package com.github.kiolk.alphabet.presentation.splash

import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.github.kiolk.alphabet.App
import com.github.kiolk.alphabet.presentation.base.BaseView

class SplashController : MvpAppCompatActivity(), BaseView {

    @InjectPresenter
    lateinit var presenter: SplashPresenter

    @ProvidePresenter
    fun providePresenter(): SplashPresenter {
        return App.component
                .plusSplashPresenter()
                .presenter
    }
}