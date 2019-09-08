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
}