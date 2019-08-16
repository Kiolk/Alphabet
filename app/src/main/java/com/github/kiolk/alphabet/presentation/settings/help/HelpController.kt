package com.github.kiolk.alphabet.presentation.settings.help

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
import kotlinx.android.synthetic.main.controller_help.view.*

class HelpController: BaseController, HelpView {

    @InjectPresenter
    lateinit var presenter: HelpPresenter

    constructor(): super()
    constructor(args: Bundle): super(args)

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup): View = inflater.inflate(R.layout.controller_help, container, false)

    @OnClick(R.id.ll_help_communicate)
    fun onCommunicatePress(){

    }

    @OnClick(R.id.ll_help_add)
    fun onAddWordsPress(){

    }

    @OnClick(R.id.ll_help_finance)
    fun onFinancePress(){

    }

    @OnClick(R.id.ll_help_improve)
    fun onImprovePress(){

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