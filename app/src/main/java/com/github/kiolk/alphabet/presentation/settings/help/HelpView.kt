package com.github.kiolk.alphabet.presentation.settings.help

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.github.kiolk.common.presentation.base.BaseView

@StateStrategyType(AddToEndSingleStrategy::class)
interface HelpView : BaseView {
    fun openTelegram()
}