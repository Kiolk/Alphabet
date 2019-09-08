package com.github.kiolk.alphabet.presentation.settings.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.OnClick
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.presentation.base.controller.BaseController
import com.github.kiolk.alphabet.utils.getContext
import com.github.kiolk.alphabet.utils.openUrl
import com.github.kiolk.alphabet.utils.sendEmail

class AboutController: BaseController, AboutView {

    @BindView(R.id.tv_about_version)
    lateinit var tvVersion: TextView

    @BindView(R.id.tv_about_title)
    lateinit var tvAboutTitle: TextView

    constructor(): super()

    constructor(args: Bundle): super(args)

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup): View = inflater.inflate(R.layout.controller_about, container, false)

    @InjectPresenter
    lateinit var presenter: AboutPresenter

    override fun setAppVersion(version: String) {
        tvVersion.text = version
    }

    override fun setAppName() {
        tvAboutTitle.text = getContext().resources.getString(R.string.app_name)
    }

    @OnClick(R.id.tv_developer_mail_about)
    fun onDeveloperEmailPress(){
        sendEmail("tyteishi@gmail.com")
    }

    @OnClick(R.id.tv_designer_first_link_about)
    fun onDesignerFirstLinkOpen(){
        openUrl("t.me/kravets")
    }

    @OnClick(R.id.tv_designer_second_link_about)
    fun onDesignerSecondLinkOpen(){
        openUrl("behance.net/ikravets")
    }

    @ProvidePresenter
    fun providePresenter(): AboutPresenter{
        return getApplicationComponent()
                .plusAboutPresenter()
                .presenter
    }

    companion object {
        const val TAG = "AboutController"
    }
}
