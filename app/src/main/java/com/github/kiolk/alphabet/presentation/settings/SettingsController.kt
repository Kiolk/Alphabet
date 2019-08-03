package com.github.kiolk.alphabet.presentation.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.OnClick
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.bluelinelabs.conductor.changehandler.VerticalChangeHandler
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.presentation.base.controller.BaseController
import com.github.kiolk.alphabet.presentation.dialogs.RateDialog
import com.github.kiolk.alphabet.presentation.settings.about.AboutController

class SettingsController : BaseController, SettingsView {

    constructor() : super()

    constructor(args: Bundle) : super()

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup): View = inflater.inflate(R.layout.controller_settings, container, false)

    @InjectPresenter
    lateinit var presenter: SettingsPresenter

    @OnClick(R.id.btn_settings_rate)
    fun onRateClick() {
        router.pushController(RouterTransaction.with(RateDialog())
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler()).tag(RateDialog.TAG))
    }

    @OnClick(R.id.btn_settings_about)
    fun onAboutPress() {
        router.pushController(RouterTransaction.with(AboutController())
                .pushChangeHandler((FadeChangeHandler()))
                .popChangeHandler(FadeChangeHandler()).tag(AboutController.TAG))
    }

    @ProvidePresenter
    fun providePresenter(): SettingsPresenter {
        return getApplicationComponent()
                .plusSettingsPresenter()
                .presenter
    }

    companion object {
        const val TAG = "SettingsController"
    }
}