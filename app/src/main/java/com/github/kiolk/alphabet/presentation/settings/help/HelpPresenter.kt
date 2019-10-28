package com.github.kiolk.alphabet.presentation.settings.help

import com.arellomobile.mvp.InjectViewState
import com.github.kiolk.alphabet.presentation.base.BasePresenter
import javax.inject.Inject

@InjectViewState
class HelpPresenter
@Inject
constructor(): BasePresenter<HelpView>() {
    fun onCommunicatePress() {
        viewState.openTelegram()
    }

    fun onSharePress() {
        //TODO replace on correct link
        viewState.shareLink("https://play.google.com/store/apps/details?id=com.triposo.droidguide.belarus&hl=en")
    }
}