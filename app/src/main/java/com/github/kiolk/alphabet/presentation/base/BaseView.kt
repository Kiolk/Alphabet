package com.github.kiolk.alphabet.presentation.base

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.github.kiolk.alphabet.data.models.game.GameResult

@StateStrategyType(AddToEndSingleStrategy::class)
interface  BaseView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class, tag = SHOW_PROGRESS_TAG)
    fun showProgress(){}

    @StateStrategyType(AddToEndSingleStrategy::class, tag = SHOW_PROGRESS_TAG)
    fun hideProgress(){}

    companion object {
        private const val SHOW_PROGRESS_TAG = "SHOW_PROGRESS_TAG"
    }
}