package com.github.kiolk.alphabet.presentation.game.preview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import butterknife.BindView
import butterknife.OnClick
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.data.models.game.GameItem
import com.github.kiolk.alphabet.data.models.game.GameResult
import com.github.kiolk.alphabet.data.models.game.GameSettings
import com.github.kiolk.alphabet.di.modules.presenter.GamePreviewPresenterModule
import com.github.kiolk.alphabet.presentation.base.controller.BaseController
import com.github.kiolk.alphabet.presentation.game.game.GameController
import com.github.kiolk.alphabet.utils.BundleBuilder

class GamePreviewController : BaseController, GamePreviewView {

    constructor(gameSettings: GameSettings) : super(BundleBuilder(Bundle()).setParseleable(BUNDLE_GAME_SETTINGS, gameSettings).build())
    constructor(args: Bundle) : super(args)

    @BindView(R.id.btn_preview_start)
    lateinit var btnStatr: Button

    @InjectPresenter
    lateinit var presenter: GamePreviewPresenter

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup): View = inflater.inflate(R.layout.controller_game_previe, container, false)

    @ProvidePresenter
    fun providePresenter(): GamePreviewPresenter {
        return getApplicationComponent()
                .plusGamePreviewComponent(GamePreviewPresenterModule(args.getParcelable(BUNDLE_GAME_SETTINGS) as GameSettings
                        ?: GameSettings("", "", "", "", "", 5, false, false)))
                .presenter
    }

    override fun setGameImage(image: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setGamePhotos(image: List<String>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setTitle(title: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun startGame(result: GameResult) {
        router.pushController(RouterTransaction.with(GameController(result))
                .pushChangeHandler(HorizontalChangeHandler())
                .popChangeHandler(HorizontalChangeHandler()).tag(GameController.TAG))
        router.getControllerWithTag(GamePreviewController.TAG)?.let { router.popController(it) }
    }

    @OnClick(R.id.btn_preview_start)
    fun onStartClick(){
        presenter.onStartClick()
    }

    companion object {
        private const val BUNDLE_GAME_SETTINGS = "BUNDLE_GAME_SETTINGS"
        const val TAG = "GAME_PREVIEW_CONTROLLER"
    }
}