package com.github.kiolk.alphabet.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.data.models.letter.Letter
import com.github.kiolk.alphabet.di.modules.presenter.HomePresenterModule
import com.github.kiolk.alphabet.presentation.base.controller.BaseController
import com.github.kiolk.alphabet.utils.BundleBuilder

class HomeController : BaseController, HomeView {

    constructor(letter: Letter) : super(BundleBuilder(Bundle()).setParseleable(BUNDLE_LETTER, letter).build())
    constructor(bundle: Bundle) : super(bundle)

    @InjectPresenter
    lateinit var presenter: HomePresenter

    @BindView(R.id.tv_home_letter_title)
    lateinit var tvLetterTitle : TextView

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.controller_home, container, false)
        return view
    }

    override fun showProgress() {
    }

    override fun hideProgress() {

    }

    override fun setTitle(title: String) {
        tvLetterTitle.text = title
    }

    @ProvidePresenter
    fun providePresenter(): HomePresenter {
        return getApplicationComponent()
                .plusHomePresenter(HomePresenterModule(args.getParcelable(BUNDLE_LETTER) ?: Letter("a", "a", "a")))
                .presenter
    }

    companion object {
        const val BUNDLE_LETTER: String = "BUNDLE_LETTER"
    }
}