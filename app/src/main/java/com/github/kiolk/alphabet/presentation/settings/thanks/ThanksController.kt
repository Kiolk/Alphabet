package com.github.kiolk.alphabet.presentation.settings.thanks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.presentation.base.controller.BaseController

class ThanksController: BaseController, ThanksView {

    @InjectPresenter
    lateinit var presenter: ThanksPresenter

    constructor(): super()
    constructor(args: Bundle): super(args)

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup): View = inflater.inflate(R.layout.controller_thanks, container, false)

    @ProvidePresenter
    fun providePresenter(): ThanksPresenter{
        return getApplicationComponent()
                .plusThanksPresenter()
                .presenter
    }

    companion object{
        const val TAG = "ThanksController"
    }
}