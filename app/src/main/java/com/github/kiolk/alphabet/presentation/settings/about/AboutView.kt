package com.github.kiolk.alphabet.presentation.settings.about

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.github.kiolk.alphabet.presentation.base.BaseView

@StateStrategyType(AddToEndSingleStrategy::class)
interface AboutView: BaseView {

    fun setAppVersion(version: String)

    fun setAppName()
}