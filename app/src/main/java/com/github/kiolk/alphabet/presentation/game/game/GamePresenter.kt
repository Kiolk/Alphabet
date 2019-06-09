package com.github.kiolk.alphabet.presentation.game.game

import android.os.Handler
import com.arellomobile.mvp.InjectViewState
import com.github.kiolk.alphabet.data.SoundManager
import com.github.kiolk.alphabet.data.models.game.GameResult
import com.github.kiolk.alphabet.data.models.word.Word
import com.github.kiolk.alphabet.presentation.base.BasePresenter
import javax.inject.Inject

@InjectViewState
class GamePresenter
@Inject
constructor(private val result: GameResult,
            private val soundManager: SoundManager) : BasePresenter<GameView>() {

    private var counter: Int = 0
    private var isWordVisible : Boolean = true
    private var isWordAnswered : Boolean = false

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
            viewState.showResult(result)
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