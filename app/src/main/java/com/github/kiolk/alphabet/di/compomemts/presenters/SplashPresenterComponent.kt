package com.github.kiolk.alphabet.di.compomemts.presenters

import com.github.kiolk.alphabet.di.scopes.Presenter
import com.github.kiolk.alphabet.presentation.splash.SplashPresenter
import dagger.Subcomponent

@Presenter
@Subcomponent
interface SplashPresenterComponent {

    val presenter: SplashPresenter
}