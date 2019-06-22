package com.github.kiolk.alphabet.presentation.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.github.kiolk.alphabet.App
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.presentation.base.BaseView
import com.github.kiolk.alphabet.presentation.words.WordsScreen

class SplashController : MvpAppCompatActivity(), SplashView, BaseView {

    @InjectPresenter
    lateinit var presenter: SplashPresenter

    var handler: Handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        handler.postDelayed({ openMainScreen() }, 200000)
    }

    override fun openMainScreen() {
        val intent = Intent(this, WordsScreen::class.java)
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }

    @ProvidePresenter
    fun providePresenter(): SplashPresenter {
        return App.component
                .plusSplashPresenter()
                .presenter
    }
}