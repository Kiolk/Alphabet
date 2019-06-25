package com.github.kiolk.alphabet.presentation.game.result

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.github.kiolk.alphabet.data.models.game.GameSettings
import com.github.kiolk.alphabet.presentation.base.BaseView

@StateStrategyType(AddToEndSingleStrategy::class)
interface ResultView : BaseView {

    fun showCorrect(stats: String)

    fun showPicture(resource: String)

    fun showCongratulations(resId: Int)

    fun setNextButtonTitle(resId: Int)

    fun setNextButtonEnable()

    fun setNextButtonDisable()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun closeNext(result: GameSettings)
}