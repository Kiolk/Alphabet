package com.github.kiolk.alphabet.presentation.settings.thanks

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.github.kiolk.common.presentation.base.BaseView

@StateStrategyType(AddToEndSingleStrategy::class)
interface ThanksView: BaseView {

    fun showAuthors(authors: List<String>)
}