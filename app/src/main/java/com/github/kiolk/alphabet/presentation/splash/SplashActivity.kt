package com.github.kiolk.alphabet.presentation.splash

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.github.kiolk.alphabet.App
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.presentation.words.WordsScreen
import com.github.kiolk.common.presentation.base.BaseView

class SplashActivity : MvpAppCompatActivity(), SplashView, BaseView {

    @InjectPresenter
    lateinit var presenter: SplashPresenter

    private var handler: Handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    override fun openMainScreen() {
        handler.postDelayed({
            val intent = Intent(this, WordsScreen::class.java)
            startActivity(intent)
            finish()
        }, 2000)
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