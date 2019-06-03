package com.github.kiolk.alphabet.presentation.game.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.kiolk.alphabet.data.models.game.GameResult
import com.github.kiolk.alphabet.presentation.base.controller.BaseController
import com.github.kiolk.alphabet.utils.BundleBuilder

class ResultController : BaseController, ResultView {

//    @InjectPresenter
//    lateinit var presenter: ResultPresenter

    constructor() : super(Bundle())
    constructor(gameResult: GameResult) : super(BundleBuilder(Bundle()).setParseleable(BUNDLE_GAME_RESULT, gameResult).build())

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup): View {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showCorrect(correct: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showWrong(wrong: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showCongratiluations() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showSad() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun closeGame(result: GameResult) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

//    @ProvidePresenter
//    fun provideResultPresenter() : ResultPresenter{
//        return getApplicationComponent().
//                plusResultPresenter(ResultPresenterModule(args.getParcelable(BUNDLE_GAME_RESULT)))
//    }

    companion object {
        private const val BUNDLE_GAME_RESULT : String = "BUNDLE_GAME_RESULT"
    }
}