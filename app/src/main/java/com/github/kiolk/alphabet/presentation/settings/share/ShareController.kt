package com.github.kiolk.alphabet.presentation.settings.share

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.OnClick
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.presentation.base.controller.BaseController
import com.github.kiolk.alphabet.utils.getString

class ShareController: BaseController, ShareView {

    constructor(): super()
    constructor(args: Bundle): super(args)

    @InjectPresenter
    lateinit var presenter: SharePresenter

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup): View = inflater.inflate(R.layout.controller_share, container, false)

    override fun shareLink(link: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plaine"
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.share_subject))
        intent.putExtra(Intent.EXTRA_TEXT, "${getString(R.string.launch_you_app)} $link")
        startActivity(Intent.createChooser(intent, getString(R.string.choose_how_send)))
    }

    override fun onBackPressed() {
        router.popController(this)
    }

    @OnClick(R.id.btn_share_back)
    fun onBackPress(){
        presenter.onBackPressed()
    }

    @OnClick(R.id.btn_share_link)
    fun onLinkPress(){
        presenter.onSharePressed()
    }

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