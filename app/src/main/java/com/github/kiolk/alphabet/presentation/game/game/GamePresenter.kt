package com.github.kiolk.alphabet.presentation.game.game

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.data.SoundManager
import com.github.kiolk.alphabet.data.domain.UpdateGameUseCase
import com.github.kiolk.alphabet.data.domain.levels.RateUseCase
import com.github.kiolk.alphabet.data.domain.player.CheckNextLevelUseCase
import com.github.kiolk.alphabet.data.domain.player.UpdatePlayerStarsUseCase
import com.github.kiolk.alphabet.data.domain.words.PrepareGameUseCase
import com.github.kiolk.alphabet.data.domain.words.UpdateCorrectWordUseCase
import com.github.kiolk.alphabet.data.models.game.GameResult
import com.github.kiolk.alphabet.data.models.game.GameSettings
import com.github.kiolk.alphabet.data.models.game.GameStats
import com.github.kiolk.alphabet.data.models.level.Level
import com.github.kiolk.alphabet.data.models.level.LevelType
import com.github.kiolk.alphabet.data.models.word.Word
import com.github.kiolk.alphabet.data.source.settings.SettingsRepository
import com.github.kiolk.alphabet.presentation.base.BasePresenter
import com.github.kiolk.alphabet.utils.RxSchedulerProvider
import com.github.kiolk.alphabet.utils.numberOfStars
import com.github.kiolk.alphabet.utils.selectLetter
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@InjectViewState
class GamePresenter
@Inject
constructor(private val result: GameResult,
            private val soundManager: SoundManager,
            private val updateGameUseCase: UpdateGameUseCase,
            private val rxSchedulerProvider: RxSchedulerProvider,
            private val settingsRepository: SettingsRepository,
            private val updateCorrectWordUseCase: UpdateCorrectWordUseCase,
            private val prepareGameUseCase: PrepareGameUseCase,
            private val updatePlayerStarsUseCase: UpdatePlayerStarsUseCase,
            private val checkNextLevelUseCase: CheckNextLevelUseCase,
            private val rateUseCase: RateUseCase) : BasePresenter<GameView>() {

    private var counter: Int = 0
    private val total: Int by lazy { result.gameItems.size }
    private var step: Int = 0
    private var isWordVisible: Boolean = true
    private var isWordAnswered: Boolean = false
    private var nextAvailableGame: GameSettings? = null
    private var previewsGame: GameSettings? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setStatusBarColor(R.color.general_dark_blue)

        counter = result.gameItems.size
        onNextWordPress()
        if (result.gameSettings != null) {
            result.gameSettings?.gameSchema?.letterValue?.let { viewState.setLetter(it) }
        } else {

            result.topic?.title?.let { viewState.setLetter(it) }
        }
    }

    override fun attachView(view: GameView?) {
        super.attachView(view)
        viewState.setStatusBarColor(R.color.general_dark_blue)
    }

    fun onCheckAnswer(word: Word) {
        if (isWordVisible) {
            return
        }

        val current = result.gameItems.get(counter).currentWord

        if (word == current) {
            soundManager.playCorrect()
            ++result.correctAnswers
            updtaeCorrectWord(current)
        } else {
            soundManager.playWrong()
            ++result.wrongAnswers
        }
        viewState.setAnswer(word, current)
        viewState.showTapButton()
        viewState.showWord()
        isWordAnswered = true
    }

    fun onNextWordPress() {
        if (counter > 0) {
            setStep()
            --counter
            viewState.hideImages()
            viewState.setWordPictures(result.gameItems.get(counter).photoItems)
            val word = result.gameItems.get(counter).currentWord.value
            val letter = result.gameSettings?.gameSchema?.letterValue ?: ""
            viewState.setWord(selectLetter(word, letter))
            isWordVisible = true
        } else {
            if (result.gameSettings != null) {
                showResult(result.gameSettings)
            } else if (result.topic != null) {
                viewState.showCompleteTopicDialog(result.topic, true)
            }
        }
    }

    fun onPreviewClick() {
        previewsGame?.let { startGame(it) }
    }

    fun onRepeatClick() {
        result.gameSettings?.let { startGame(it) }
    }

    fun onNextClick() {
        nextAvailableGame?.let { startGame(it) }
    }

    private fun showResult(gameSettings: GameSettings) {
        val isCompleted = result.correctAnswers / result.gameItems.size.toFloat() > 0.5
        if (isCompleted) {
            gameSettings.isCompleted = isCompleted
            val getStars = numberOfStars(result.correctAnswers, result.correctAnswers + result.wrongAnswers)

            if (getStars > gameSettings.stars) {
                updateProfile(getStars - gameSettings.stars)
                gameSettings.stars = getStars
            }

            addDisposable(updateGameUseCase.execute(UpdateGameUseCase.Params(gameSettings))
                    .compose(rxSchedulerProvider.getIoToMainTransformerSingle())
                    .subscribe(this::onNextAvailableGame, this::onErrorAvailableGame))
        } else {
            addDisposable(settingsRepository.getNextAvailableSettings(gameSettings)
                    .compose(rxSchedulerProvider.getIoToMainTransformerSingle())
                    .subscribe(this::onNextAvailableGame, this::onErrorAvailableGame))
        }
    }

    private fun updateProfile(addStars: Int) {
        addDisposable(checkNextLevelUseCase.execute(CheckNextLevelUseCase.Params(addStars))
                .delay(50, TimeUnit.MILLISECONDS)
                .compose(rxSchedulerProvider.getIoToMainTransformerSingle())
                .subscribe(this::onAcceptNextLevel, Timber::e))
    }

    private fun onAcceptNextLevel(result: Pair<LevelType?, Int>) {
        val level = result.first
        val stars = result.second

        addDisposable(updatePlayerStarsUseCase.execute(UpdatePlayerStarsUseCase.Params(stars))
                .delay(1, TimeUnit.SECONDS)
                .compose(rxSchedulerProvider.goIoToMainTransformerComplitable())
                .subscribe())

        if (level != null) {
            viewState.showCompleteLevelDialog(level)
        }
    }

    fun onNextAvailableGame(gamePair: Pair<GameSettings?, GameSettings?>) {
        this.nextAvailableGame = gamePair.second
        this.previewsGame = gamePair.first
        val stats = GameStats(result.gameItems.size, result.correctAnswers, numberOfStars(result.correctAnswers, result.gameItems.size),
                gamePair.first != null, gamePair.second != null)

        if (nextAvailableGame != null) {
            nextAvailableGame?.isAvailable = true
            addDisposable(settingsRepository.updateSetting(nextAvailableGame!!)
                    .compose(rxSchedulerProvider.goIoToMainTransformerComplitable())
                    .subscribe())
        }

        viewState.showResult(stats)
//        viewState.showCompleteLevelDialog(Level(R.drawable.ic_duck,"Kachka"))
    }

    fun onErrorAvailableGame(throwable: Throwable) {
//        viewState.showLevelComplete()
    }

    fun onTapClick() {
        onNextWordPress()
        viewState.hideTapButton()
        viewState.showWord()
        isWordVisible = true
        isWordAnswered = false
    }

    fun onWordClick() {
        if (isWordAnswered) {
            return
        }

        if (isWordVisible) {
            viewState.hideWord()
            viewState.hideBlurHolder()
            viewState.showImages()
        } else {
            viewState.showWord()
            viewState.hideImages()
        }
        isWordVisible = !isWordVisible
    }

    private fun updtaeCorrectWord(word: Word) {
        addDisposable(updateCorrectWordUseCase.execute(UpdateCorrectWordUseCase.Params(word))
                .compose(rxSchedulerProvider.goIoToMainTransformerComplitable())
                .subscribe())
    }

    private fun setStep() {
        ++step
        viewState.setStep("$step/$total")
    }

    private fun startGame(gameSettings: GameSettings) {
        addDisposable(prepareGameUseCase.execute(PrepareGameUseCase.Params(gameSettings))
                .compose(rxSchedulerProvider.getIoToMainTransformerSingle())
                .subscribe({
                    viewState.closeGame()
                    viewState.startGame(it)
                }, Timber::e))

    }

    fun onCloseDialog() {
        viewState.pop()
    }

    fun onCloseGameClick() {
        viewState.onCloseGame()
    }

    fun onMistakeClicked() {
        viewState.showMistakeDialog(result.gameItems[counter])
    }
}