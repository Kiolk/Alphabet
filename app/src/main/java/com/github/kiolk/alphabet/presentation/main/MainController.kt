package com.github.kiolk.alphabet.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.presentation.base.controller.BaseController

class MainController : BaseController, MainView {

    @BindView(R.id.tv_main_level_name)
    lateinit var tvLevelTitle: TextView

    @BindView(R.id.iv_main_level_icon)
    lateinit var ivLevelImage: ImageView

    constructor() : super()
    constructor(args: Bundle) : super(args)

    @InjectPresenter
    lateinit var presenter: MainPresenter

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup) = inflater
            .inflate(R.layout.controller_main, container, false)

    override fun showLevelTitle(title: String) {
        tvLevelTitle.text = title
    }

    override fun showLevelImage(imageRes: Int) {
        ivLevelImage.setImageResource(imageRes)
    }

    @ProvidePresenter
    fun providePresenter(): MainPresenter {
        return getApplicationComponent()
                .plusMainPresenter()
                .presenter
    }
}