package com.github.kiolk.alphabet.presentation.home

import android.text.SpannableStringBuilder
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.github.kiolk.alphabet.presentation.base.BaseView

@StateStrategyType(AddToEndSingleStrategy::class)
interface HomeView : BaseView {

    fun setTitle(title : String)

    fun setExample(spannable: SpannableStringBuilder)

    fun setLetterImage(source: String)
}