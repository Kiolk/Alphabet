package com.github.kiolk.alphabet.presentation.settings.help

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.OnClick
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.presentation.base.controller.BaseController
import com.github.kiolk.alphabet.utils.Constants
import com.github.kiolk.alphabet.utils.openUrl
import kotlinx.android.synthetic.main.controller_help.view.*

class HelpController: BaseController, HelpView {

    @InjectPresenter
    lateinit var presenter: HelpPresenter

    constructor(): super()
    constructor(args: Bundle): super(args)

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup): View = inflater.inflate(R.layout.controller_help, container, false)

    override fun openTelegram() {
        val telegramIntent = Intent(Intent.ACTION_VIEW, Uri.parse(Constants.ALPHABET_TELEGRAM))
        activity?.startActivity(telegramIntent)
    }

    @OnClick(R.id.ll_help_communicate)
    fun onCommunicatePress(){
        presenter.onCommunicatePress()
    }

    @OnClick(R.id.ll_help_add)
    fun onAddWordsPress(){
        openUrl("https://github.com/Kiolk/Alphabet/blob/develop/docs/Words.md")
    }

    @OnClick(R.id.ll_help_finance)
    fun onFinancePress(){
        openUrl("https://github.com/Kiolk/Alphabet/blob/develop/docs/FinanceHelp.md")
    }

    @OnClick(R.id.ll_help_improve)
    fun onImprovePress(){
        openUrl("https://github.com/Kiolk/Alphabet/blob/develop/docs/ImproveCode.md")
    }

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