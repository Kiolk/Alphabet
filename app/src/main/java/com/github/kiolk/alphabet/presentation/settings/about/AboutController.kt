package com.github.kiolk.alphabet.presentation.settings.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.presentation.base.controller.BaseController


class AboutController: BaseController, AboutView {

    @BindView(R.id.tv_about_version)
    lateinit var tvVersion: TextView

    constructor(): super()

    constructor(args: Bundle): super(args)

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup): View = inflater.inflate(R.layout.controller_about, container, false)

    @InjectPresenter
    lateinit var presenter: AboutPresenter

    override fun setAppVersion(version: String) {
        tvVersion.text = version
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
