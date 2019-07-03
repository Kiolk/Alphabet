package com.github.kiolk.alphabet.presentation.game.game

import android.os.Handler
import com.arellomobile.mvp.InjectViewState
import com.github.kiolk.alphabet.data.SoundManager
import com.github.kiolk.alphabet.data.domain.UpdateGameUseCase
import com.github.kiolk.alphabet.data.models.game.GameResult
import com.github.kiolk.alphabet.data.models.game.GameSettings
import com.github.kiolk.alphabet.data.models.game.GameStats
import com.github.kiolk.alphabet.data.models.word.Word
import com.github.kiolk.alphabet.data.source.settings.SettingsRepository
import com.github.kiolk.alphabet.presentation.base.BasePresenter
import com.github.kiolk.alphabet.utils.RxSchedulerProvider
import javax.inject.Inject

@InjectViewState
class GamePresenter
@Inject
constructor(private val result: GameResult,
            private val soundManager: SoundManager,
            private val updateGameUseCase: UpdateGameUseCase,
            private val rxSchedulerProvider: RxSchedulerProvider,
            private val settingsRepository: SettingsRepository) : BasePresenter<GameView>() {

    private var counter: Int = 0
    private var isWordVisible : Boolean = true
    private var isWordAnswered : Boolean = false
    private var nextAvailableGame: GameSettings? = null
    private var previewsGame: GameSettings? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        counter = result.gameItems.size
        onNextWordPress()
    }

    fun onCheckAnswer(word: Word) {
        if(isWordVisible){
            return
        }

        if (word.equals(result.gameItems.get(counter).currentWord)) {
            soundManager.playCorrect()
            ++result.correctAnswers
        } else {
            soundManager.playWrong()
            ++result.wrongAnswers
        }
        viewState.setAnswer(word, result.gameItems.get(counter).currentWord)
        viewState.showTapButton()
        viewState.showWord()
        isWordAnswered = true
    }

    fun onNextWordPress() {
        if (counter > 0) {
            --counter
            Handler().postDelayed({
                viewState.showBlurHolder()
            }, 500)
//            viewState.showBlurHolder()
            viewState.setWordPictures(result.gameItems.get(counter).photoItems)
            viewState.setWord(result.gameItems.get(counter).currentWord.value)
            isWordVisible = true
        } else {
//            viewState.showResult(result)
            showResult()
        }
    }

    fun onPreviewClick(){
        previewsGame?.let { viewState.startGame(it) }
    }

    fun onRepeatClick() {
        viewState.startGame(result.gameSettings)
    }

    fun onNextClick(){
        nextAvailableGame?.let { viewState.startGame(it) }
    }

    private fun showResult() {
        val isCompleted = result.correctAnswers / result.gameItems.size > 0.75
        if (isCompleted) {
            result.gameSettings.isCompleted = isCompleted

            addDisposable(updateGameUseCase.execute(UpdateGameUseCase.Params(result.gameSettings))
                    .compose(rxSchedulerProvider.getIoToMainTransformerSingle())
                    .subscribe(this::onNextAvailableGame, this::onErrorAvailableGame))
        }else{
            addDisposable(settingsRepository.getNextAvailableSettings(result.gameSettings)
                    .compose(rxSchedulerProvider.getIoToMainTransformerSingle())
                    .subscribe(this::onNextAvailableGame, this::onErrorAvailableGame))
//            val stats = GameStats(result.gameItems.size, result.correctAnswers, 0)
//            viewState.showResult(stats)
        }
    }

    fun onNextAvailableGame(gamePair: Pair<GameSettings?, GameSettings?> ){
        this.nextAvailableGame = gamePair.second
        this.previewsGame = gamePair.first
        val stats = GameStats(result.gameItems.size, result.correctAnswers, 1,
                gamePair.first != null, gamePair.second != null)

        if(nextAvailableGame != null){
            nextAvailableGame?.isAvailable = true
            addDisposable(settingsRepository.updateSetting(nextAvailableGame!!)
                    .compose(rxSchedulerProvider.goIoToMainTransformerComplitable())
                    .subscribe())
        }

        viewState.showResult(stats)
    }

    fun onErrorAvailableGame(throwable: Throwable){
        viewState.showLevelComplete()
    }

    fun onTapClick() {
        onNextWordPress()
        viewState.hideTapButton()
        viewState.showWord()
        isWordVisible = true
        isWordAnswered = false
    }

    fun onWordClick() {
        if(isWordAnswered){
            return
        }

        if(isWordVisible){
            viewState.hideWord()
            viewState.hideBlurHolder()
        }else{
            viewState.showWord()
            viewState.showBlurHolder()
        }
        isWordVisible = !isWordVisible
    }
}