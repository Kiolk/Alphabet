package com.github.kiolk.alphabet.presentation.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.OnClick
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.bluelinelabs.conductor.changehandler.VerticalChangeHandler
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.presentation.base.controller.BaseController
import com.github.kiolk.alphabet.presentation.dialogs.RateDialog
import com.github.kiolk.alphabet.presentation.dialogs.ResetGameDialog
import com.github.kiolk.alphabet.presentation.main.MainController
import com.github.kiolk.alphabet.presentation.settings.about.AboutController
import com.github.kiolk.alphabet.presentation.settings.help.HelpController
import com.github.kiolk.alphabet.presentation.settings.policy.PolicyController
import com.github.kiolk.alphabet.presentation.settings.thanks.ThanksController
import com.github.kiolk.alphabet.presentation.settings.thanks.ThanksPresenter

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

    @OnClick(R.id.btn_settings_help)
    fun onHelpPress(){
        router.pushController(RouterTransaction.with(HelpController())
                .popChangeHandler(FadeChangeHandler())
                .pushChangeHandler(FadeChangeHandler())
                .tag(HelpController.TAG))
    }

    @OnClick(R.id.btn_settings_policy)
    fun onPolicyPress(){
        router.pushController(RouterTransaction.with(PolicyController())
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler())
                .tag(PolicyController.TAG))
    }

    @OnClick(R.id.btn_settings_thanks)
    fun onThanksPress(){
        router.pushController(RouterTransaction.with(ThanksController())
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler())
                .tag(ThanksController.TAG))
    }

    @OnClick(R.id.brn_setting_reset_game)
    fun onResetClick(){
        router.pushController((RouterTransaction.with(ResetGameDialog.getInstance {
            presenter.onResetPress()
        }))
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler())
                .tag(ResetGameDialog.TAG))
    }

    override fun openMainScreen() {
        router.setRoot(RouterTransaction.with(MainController())
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler())
                .tag(MainController.TAG))

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