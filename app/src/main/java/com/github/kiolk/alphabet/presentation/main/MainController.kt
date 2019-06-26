package com.github.kiolk.alphabet.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.presentation.base.controller.BaseController

class MainController : BaseController, MainView {

    constructor() : super()
    constructor(args: Bundle) : super(args)

    @InjectPresenter
    lateinit var presenter: MainPresenter

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup) = inflater
            .inflate(R.layout.controller_main, container, false)

    @ProvidePresenter
    fun providePresenter(): MainPresenter {
        return getApplicationComponent()
                .plusMainPresenter()
                .presenter
    }
}