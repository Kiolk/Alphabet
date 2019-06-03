package com.github.kiolk.alphabet.presentation.game.result

import com.github.kiolk.alphabet.data.models.game.GameResult
import com.github.kiolk.alphabet.presentation.base.BaseView

interface ResultView : BaseView {

    fun showCorrect(correct : Int)

    fun showWrong(wrong : Int)

    fun showCongratiluations()

    fun showSad()

    fun closeGame( result : GameResult)
}