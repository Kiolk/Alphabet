package com.github.kiolk.alphabet.presentation.settings.share

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.presentation.base.controller.BaseController

class ShareController: BaseController, ShareView {

    constructor(): super()
    constructor(args: Bundle): super(args)

    @InjectPresenter
    lateinit var presenter: SharePresenter

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup): View = inflater.inflate(R.layout.controller_share, container, false)

    @ProvidePresenter
    fun providePresenter(): SharePresenter{
        return getApplicationComponent()
                .plusSharePresenter()
                .presenter
    }

    companion object {
        const val TAG = "ShareController"
    }
}