package com.github.kiolk.alphabet.presentation.home

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bumptech.glide.Glide
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.data.models.letter.Letter
import com.github.kiolk.alphabet.di.modules.presenter.HomePresenterModule
import com.github.kiolk.alphabet.presentation.base.controller.BaseController
import com.github.kiolk.alphabet.utils.BundleBuilder

class HomeController : BaseController, HomeView {

    @BindView(R.id.tv_home_letter_title)
    lateinit var tvLetterTitle : TextView

    @BindView(R.id.iv_home_image)
    lateinit var ivLetterImage: ImageView

    @BindView(R.id.tv_home_example_word)
    lateinit var tvExampleWord: TextView

    @InjectPresenter
    lateinit var presenter: HomePresenter

    constructor(letter: Letter) : super(BundleBuilder(Bundle()).setParseleable(BUNDLE_LETTER, letter).build())
    constructor(bundle: Bundle) : super(bundle)

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.controller_home, container, false)
        return view
    }

    override fun setTitle(title: String) {
        tvLetterTitle.text = title
    }

    override fun setExample(spannable: SpannableStringBuilder) {
        tvExampleWord.setText(spannable)
    }

    override fun setLetterImage(source: String) {
        Glide.with(ivLetterImage)
                .load(source)
                .into(ivLetterImage)
    }

    @ProvidePresenter
    fun providePresenter(): HomePresenter {
        return getApplicationComponent()
                .plusHomePresenter(HomePresenterModule(args.getParcelable(BUNDLE_LETTER) ?: Letter("a", "a", "a", letterWord = "Аўтобус")))
                .presenter
    }

    companion object {
        const val BUNDLE_LETTER: String = "BUNDLE_LETTER"
    }
}