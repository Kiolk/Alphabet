package com.github.kiolk.alphabet.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.OnClick
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.presentation.base.controller.BaseController
import com.github.kiolk.alphabet.presentation.views.LevelLebel
import com.github.kiolk.alphabet.presentation.views.StyledProgressBar
import com.github.kiolk.alphabet.presentation.words.WordsScreen

class MainController : BaseController, MainView {

    @BindView(R.id.tv_main_level_name)
    lateinit var tvLevelTitle: TextView

    @BindView(R.id.ll_main_level_label)
    lateinit var ivLevelLabel: LevelLebel

    @BindView(R.id.pb_maine_level_progress)
    lateinit var spLevelProgress: StyledProgressBar

    @BindView(R.id.tv_main_level_description)
    lateinit var tvSentence: TextView

    @BindView(R.id.tv_main_level_author)
    lateinit var tvAuthor: TextView

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

    override fun setProgress(progress: Int) {
        spLevelProgress.setProgress(progress)
    }

    override fun showLevelImage(imageRes: Int) {
        ivLevelLabel.setLevelImage(imageRes)
    }

    override fun setSentence(sentence: String) {
        tvSentence.text = sentence
    }

    override fun setAuthor(author: String) {
        tvAuthor.text = author
    }

    @OnClick(R.id.iv_main_level_setting)
    fun onSettingsPress(){
        (activity as? WordsScreen)?.showSettings()
    }

    @ProvidePresenter
    fun providePresenter(): MainPresenter {
        return getApplicationComponent()
                .plusMainPresenter()
                .presenter
    }

    companion object {
        const val TAG = "MainController"
    }
}