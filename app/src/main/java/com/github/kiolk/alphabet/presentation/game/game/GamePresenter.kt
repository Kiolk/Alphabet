package com.github.kiolk.alphabet.presentation.game.game

import android.os.Handler
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.github.kiolk.alphabet.data.SoundManager
import com.github.kiolk.alphabet.data.domain.PrepareGameSetUseCase
import com.github.kiolk.alphabet.data.models.game.GameItem
import com.github.kiolk.alphabet.data.models.game.GameResult
import com.github.kiolk.alphabet.data.models.game.GameSettings
import com.github.kiolk.alphabet.data.models.word.Word
import com.github.kiolk.alphabet.data.source.words.WordsRepository
import com.github.kiolk.alphabet.presentation.base.BasePresenter
import com.github.kiolk.alphabet.utils.RxSchedulerProvider
import javax.inject.Inject

@InjectViewState
class GamePresenter
@Inject
constructor(private val gameSettings: GameSettings,
            private val repository: WordsRepository,
            private val rxSchedulerProvider: RxSchedulerProvider,
            private val prepareGameSetUseCase: PrepareGameSetUseCase,
            private val soundManager: SoundManager) : BasePresenter<GameView>() {

    private lateinit var resullt : GameResult
    private lateinit var gameSet: List<GameItem>
    private var counter: Int = 0
    private var isWordVisible : Boolean = true
    private var isWordAnswered : Boolean = false

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        addDisposable(repository.selectWords(gameSettings.queryRegex)
                .compose(rxSchedulerProvider.goIoToMainTransformerFloweable())
                .subscribe(this::onWordSuccess, this::onError))
    }

    fun onError(throwable: Throwable) {
        Log.d("MyLogs", throwable.message)
    }

    fun onWordSuccess(wordList: List<Word>) {
        gameSet = prepareGameSetUseCase.execute(PrepareGameSetUseCase.Params(gameSettings, wordList))
        resullt = GameResult(gameSettings, gameSet, 0 ,0)
        counter = gameSet.size
        onNextWordPress()
    }

    fun onCheckAnswer(word: Word) {
        if(isWordVisible){
            return
        }

        if (word.equals(gameSet.get(counter).currentWord)) {
            soundManager.playCorrect()
            ++resullt.correctAnswers
        } else {
            soundManager.playWrong()
            ++resullt.wrongAnswers
        }
        viewState.setAnswer(word, gameSet.get(counter).currentWord)
        viewState.showTapButton()
        viewState.showWord()
        isWordAnswered = true
//        viewState.enableNextButton()
    }

    fun onNextWordPress() {
        if (counter > 0) {
            --counter
            Handler().postDelayed({
                viewState.showBlurHolder()
            }, 2000)
            viewState.showBlurHolder()
            viewState.setWordPictures(gameSet.get(counter).photoItems)
            viewState.setWord(gameSet.get(counter).currentWord.value)
            isWordVisible = true
//            viewState.disableNext()
        } else {
            viewState.showResult(resullt)
        }

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