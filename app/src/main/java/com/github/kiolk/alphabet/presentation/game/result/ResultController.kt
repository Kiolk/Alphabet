package com.github.kiolk.alphabet.presentation.game.result

import android.os.Bundle
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
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.data.models.game.GameResult
import com.github.kiolk.alphabet.data.models.game.GameSettings
import com.github.kiolk.alphabet.di.modules.presenter.ResultPresenterModule
import com.github.kiolk.alphabet.presentation.base.controller.BaseController
import com.github.kiolk.alphabet.presentation.game.preview.GamePreviewController
import com.github.kiolk.alphabet.utils.BundleBuilder
import com.github.kiolk.alphabet.utils.getString

class ResultController : BaseController, ResultView {

    @BindView(R.id.iv_game_result_picture)
    lateinit var ivPicture: ImageView

    @BindView(R.id.tv_game_result_title)
    lateinit var tvTitle: TextView

    @BindView(R.id.tv_game_result_next)
    lateinit var tvNext: TextView

    @BindView(R.id.tv_game_result_indicator)
    lateinit var tvIndicator: TextView

    @InjectPresenter
    lateinit var presenter: ResultPresenter

    constructor() : super(Bundle())
    constructor(gameResult: GameResult) : super(BundleBuilder(Bundle()).setParseleable(BUNDLE_GAME_RESULT, gameResult).build())

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup): View = inflater.inflate(R.layout.controller_game_result, container, false)

    override fun showCorrect(stats: String) {
        tvIndicator.text = stats
    }

    override fun showCongratulations(resId: Int) {
        tvTitle.text = getString(resId)
//        val endGameController = EndGameDialog.getInstance(current)
//        router.pushController(RouterTransaction.with(endGameController)
//                .popChangeHandler(VerticalChangeHandler())
//                .pushChangeHandler(VerticalChangeHandler(false)))
    }

    override fun showPicture(resource: String) {
    }

    override fun closeNext(result: GameSettings) {
        router.pushController(RouterTransaction.with(GamePreviewController(result))
                .pushChangeHandler(HorizontalChangeHandler())
                .popChangeHandler(HorizontalChangeHandler()))
        router.getControllerWithTag(TAG)?.let { router.popController(it) }
    }

    override fun setNextButtonTitle(resId: Int) {
        tvNext.text = getString(resId)
    }

    override fun setNextButtonEnable() {
        tvNext.isEnabled = true
    }

    override fun setNextButtonDisable() {
        tvNext.isEnabled = false
    }

    @ProvidePresenter
    fun provideResultPresenter(): ResultPresenter {
        return getApplicationComponent().plusResultPresenter(ResultPresenterModule(args.getParcelable(BUNDLE_GAME_RESULT)!!))
                .presenter
    }

    @OnClick(R.id.tv_game_result_next)
    fun onNextClick(){
        presenter.onNextClick()
    }

    companion object {
        const val TAG = "RESULT_CONTROLLER_TAG"
        private const val BUNDLE_GAME_RESULT: String = "BUNDLE_GAME_RESULT"
    }
}