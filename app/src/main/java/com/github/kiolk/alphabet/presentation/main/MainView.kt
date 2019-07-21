package com.github.kiolk.alphabet.presentation.main

import com.arellomobile.mvp.MvpView
import com.github.kiolk.alphabet.presentation.base.BaseView

interface MainView: BaseView {

    fun showLevelTitle(title: String)

    fun showLevelImage(imageRes: Int)
}