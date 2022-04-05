package com.github.kiolk.alphabet.presentation.game.game

import android.text.SpannableStringBuilder
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.github.kiolk.alphabet.data.models.game.GameItem
import com.github.kiolk.alphabet.data.models.game.GameResult
import com.github.kiolk.alphabet.data.models.game.GameStats
import com.github.kiolk.alphabet.data.models.topic.Topic
import com.github.kiolk.alphabet.data.models.word.Word
import com.github.kiolk.common.presentation.base.BaseView

@StateStrategyType(AddToEndSingleStrategy::class)
interface GameView : BaseView {

    fun setWord(spannable: SpannableStringBuilder)

    fun setWordPictures(list: List<Word>)

    fun setAnswer(userAnswer: Word, correct: Word)

    fun enableNextButton()

    fun disableNext()

    fun showResult(current: GameStats)

    fun hideBlurHolder()

    fun showTapButton()

    fun hideTapButton()

    fun showWord()

    fun showImages()

    fun hideImages()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun hideWord()

    fun startGame(gameResult: GameResult)

    fun setStep(step: String)

    fun setLetter(letter: String)

    fun closeGame()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showCompleteTopicDialog(topic: Topic, isGame: Boolean)

    fun onCloseGame()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showMistakeDialog(gameItem: GameItem)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun closeMistakeDialog()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showMistakeDialogError(throwable: Throwable)
}