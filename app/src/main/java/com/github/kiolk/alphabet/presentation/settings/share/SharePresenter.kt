package com.github.kiolk.alphabet.presentation.settings.share

import com.arellomobile.mvp.InjectViewState
import com.github.kiolk.alphabet.BuildConfig
import com.github.kiolk.common.presentation.base.BasePresenter
import javax.inject.Inject

@InjectViewState
class SharePresenter
@Inject constructor() : BasePresenter<ShareView>() {

    fun onBackPressed() {
        viewState.onBackPressed()
    }

    fun onSharePressed() {
        viewState.shareLink("$GOOGLE_PLAY_LINK${BuildConfig.APPLICATION_ID}")
    }

    companion object {

        private const val GOOGLE_PLAY_LINK = "https://play.google.com/store/apps/details?id="
    }
}