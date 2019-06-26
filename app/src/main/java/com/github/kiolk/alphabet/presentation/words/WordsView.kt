package com.github.kiolk.alphabet.presentation.words

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.github.kiolk.alphabet.data.models.game.GameSettings
import com.github.kiolk.alphabet.data.models.letter.Letter
import com.github.kiolk.alphabet.data.models.word.Word

interface WordsView : MvpView {

    fun setAvailableTopics(topics : List<GameSettings>)

    fun closeMenu()

    fun setAlphabet(alphabet : List<Letter>)

    fun setSelectedLetter(letter : Letter)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun endGame()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showMain()
}