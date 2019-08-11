package com.github.kiolk.alphabet.presentation.settings.help

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.presentation.base.controller.BaseController

class HelpController: BaseController {

    private lateinit var presenter: HelpPresenter

    constructor(): super()
    constructor(args: Bundle): super(args)

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup): View = inflater.inflate(R.layout.controller_help, container, false)

    @ProvidePresenter
    fun providePresenter(): HelpPresenter{
        return getApplicationComponent()
                .plusHelpPresenter()
                .presenter
    }

    companion object{
        const val TAG = "HelpController"
    }
}