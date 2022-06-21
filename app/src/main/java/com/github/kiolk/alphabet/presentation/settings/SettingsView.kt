package com.github.kiolk.alphabet.presentation.settings

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.github.kiolk.common.presentation.base.BaseView

interface SettingsView: BaseView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun openMainScreen()
}