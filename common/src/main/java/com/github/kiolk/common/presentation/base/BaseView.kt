package com.github.kiolk.common.presentation.base

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.github.kiolk.common.data.model.level.LevelType

@StateStrategyType(AddToEndSingleStrategy::class)
interface  BaseView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class, tag = SHOW_PROGRESS_TAG)
    fun showProgress(){}

    @StateStrategyType(AddToEndSingleStrategy::class, tag = SHOW_PROGRESS_TAG)
    fun hideProgress(){}

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showCompleteLevelDialog(level: LevelType){}

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setStatusBarColor(coloRes: Int){}

    fun pop(){}

    companion object {
        private const val SHOW_PROGRESS_TAG = "SHOW_PROGRESS_TAG"
    }
}