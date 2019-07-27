package com.github.kiolk.alphabet.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.presentation.base.controller.BaseController
import com.github.kiolk.alphabet.presentation.views.LevelLebel
import com.github.kiolk.alphabet.presentation.views.StyledProgressBar

class MainController : BaseController, MainView {

    @BindView(R.id.tv_main_level_name)
    lateinit var tvLevelTitle: TextView

    @BindView(R.id.ll_main_level_label)
    lateinit var ivLevelLabel: LevelLebel

    @BindView(R.id.pb_maine_level_progress)
    lateinit var spLevelProgress: StyledProgressBar

    constructor() : super()
    constructor(args: Bundle) : super(args)

    @InjectPresenter
    lateinit var presenter: MainPresenter

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup) = inflater
            .inflate(R.layout.controller_main, container, false)

    override fun showLevelTitle(title: String) {
        tvLevelTitle.text = title
    }

    override fun setCurrentStars(stars: String) {
        ivLevelLabel.setLevel(stars)
    }

    override fun setLevelStart(value: String) {
        spLevelProgress.setInitialValue(value)
    }

    override fun setLevelEnd(value: String) {
        spLevelProgress.setFinalValue(value)
    }

    override fun setPorgress(progress: Int) {
        spLevelProgress.setProgress(progress)
    }

    override fun showLevelImage(imageRes: Int) {
        ivLevelLabel.setLevelImage(imageRes)
    }

    @ProvidePresenter
    fun providePresenter(): MainPresenter {
        return getApplicationComponent()
                .plusMainPresenter()
                .presenter
    }
}