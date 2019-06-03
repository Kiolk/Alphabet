package com.github.kiolk.alphabet.presentation.game.game

import com.github.kiolk.alphabet.data.models.game.GameResult
import com.github.kiolk.alphabet.data.models.word.Word
import com.github.kiolk.alphabet.presentation.base.BaseView

interface GameView : BaseView {

    fun setWord(word: String)

    fun setWordPictures(list: List<Word>)

    fun setAnswer(userAnswer: Word, correct: Word)

    fun enableNextButton()

    fun disableNext()

    fun showResult(resullt: GameResult)

    fun showBlurHolder()

    fun hideBlurHolder()

    fun showTapButton()

    fun hideTapButton()

    fun showWord()

    fun hideWord()
}