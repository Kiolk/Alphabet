package com.github.kiolk.alphabet.presentation.settings.thanks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import butterknife.BindView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.presentation.base.controller.BaseController
import com.github.kiolk.alphabet.utils.toContentString

class ThanksController: BaseController, ThanksView {

    @BindView(R.id.tv_thanks_authors)
    lateinit var authorsView: TextView

    @BindView(R.id.pb_thanks)
    lateinit var progress: ProgressBar

    @InjectPresenter
    lateinit var presenter: ThanksPresenter

    constructor(): super()
    constructor(args: Bundle): super(args)

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup): View = inflater.inflate(R.layout.controller_thanks, container, false)

    override fun showAuthors(authors: List<String>) {
        authorsView.text = authors.toContentString()
    }

    override fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress.visibility = View.GONE
    }

    @ProvidePresenter
    fun providePresenter(): ThanksPresenter{
        return getApplicationComponent()
                .plusThanksPresenter()
                .presenter
    }

    companion object{
        const val TAG = "ThanksController"
    }
}