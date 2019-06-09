package com.github.kiolk.alphabet.presentation.game.game

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.github.kiolk.alphabet.data.models.game.GameResult
import com.github.kiolk.alphabet.data.models.word.Word
import com.github.kiolk.alphabet.presentation.base.BaseView

@StateStrategyType(AddToEndSingleStrategy::class)
interface GameView : BaseView {

    fun setWord(word: String)

    fun setWordPictures(list: List<Word>)

    fun setAnswer(userAnswer: Word, correct: Word)

    fun enableNextButton()

    fun disableNext()

    fun showResult(resullt: GameResult)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showBlurHolder()

    fun hideBlurHolder()

    fun showTapButton()

    fun hideTapButton()

    fun showWord()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun hideWord()
}