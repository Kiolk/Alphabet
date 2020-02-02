package com.github.kiolk.alphabet.presentation.main

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.OnClick
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.presentation.base.controller.BaseController
import com.github.kiolk.alphabet.presentation.dialogs.RateDialog
import com.github.kiolk.alphabet.presentation.dialogs.ResetGameDialog
import com.github.kiolk.alphabet.presentation.views.LevelLebel
import com.github.kiolk.alphabet.presentation.views.StyledProgressBar
import com.github.kiolk.alphabet.presentation.words.WordsScreen
import com.github.kiolk.alphabet.utils.getContext

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

    @BindView(R.id.cl_maine_level_final_container)
    lateinit var endGameLayout: ConstraintLayout

    @BindView(R.id.cl_main_level_base_container)
    lateinit var maunLayout: ConstraintLayout

    @BindView(R.id.iv_sound_switcher)
    lateinit var ivSoundButton: ImageView

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

    override fun showEdnGameLayout() {
        endGameLayout.visibility = View.VISIBLE
        maunLayout.visibility = View.GONE
    }

    override fun hideEndGameLayout() {
        endGameLayout.visibility = View.GONE
        maunLayout.visibility = View.VISIBLE
    }

    override fun onResetSuccess() {
        router.popToRoot()
        router.pushController(RouterTransaction.with(MainController())
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler()).tag(TAG))
    }

    override fun setSoundState(drawableId: Int) {
        ivSoundButton.setImageDrawable(getContext().resources.getDrawable(drawableId))
    }

    @OnClick(R.id.iv_main_level_setting, R.id.iv_main_level_end_setting)
    fun onSettingsPress() {
        (activity as? WordsScreen)?.showSettings()
    }

    @OnClick(R.id.iv_sound_switcher)
    fun onSoundPress() {
        presenter.onSoundPressed()
    }

    @OnClick(R.id.btn_main_level_restart)
    fun onRestartPress() {
        router.pushController((RouterTransaction.with(ResetGameDialog.getInstance {
            presenter.onResetPress()
        }))
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler())
                .tag(ResetGameDialog.TAG))
    }

    @OnClick(R.id.btn_main_level_rate)
    fun onRatePress() {
        router.pushController(RouterTransaction.with(RateDialog())
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler()).tag(RateDialog.TAG))
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