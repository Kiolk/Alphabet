package com.github.kiolk.alphabet.presentation.settings.about

import com.arellomobile.mvp.InjectViewState
import com.github.kiolk.alphabet.BuildConfig
import com.github.kiolk.common.presentation.base.BasePresenter
import javax.inject.Inject

@InjectViewState
class AboutPresenter
@Inject
constructor() : BasePresenter<AboutView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.setAppVersion(BuildConfig.VERSION_NAME)
        viewState.setAppName()
    }
}