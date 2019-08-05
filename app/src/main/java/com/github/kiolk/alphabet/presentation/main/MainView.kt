package com.github.kiolk.alphabet.presentation.main

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.github.kiolk.alphabet.presentation.base.BaseView

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView: BaseView {

    fun showLevelTitle(title: String)

    fun showLevelImage(imageRes: Int)

    fun setCurrentStars(stars: String)

    fun setLevelStart(value: String)

    fun setLevelEnd(value: String)

    fun setProgress(progress: Int)

    fun setSentence(sentence: String)

    fun setAuthor(author: String)
}