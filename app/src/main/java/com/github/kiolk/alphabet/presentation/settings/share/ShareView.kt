package com.github.kiolk.alphabet.presentation.settings.share

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.github.kiolk.alphabet.presentation.base.BaseView

@StateStrategyType(OneExecutionStateStrategy::class)
interface ShareView: BaseView {

    fun shareLink(link: String)

    fun onBackPressed()
}