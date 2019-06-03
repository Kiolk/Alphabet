package com.github.kiolk.alphabet.di.compomemts.presenters

import com.github.kiolk.alphabet.di.modules.presenter.HomePresenterModule
import com.github.kiolk.alphabet.di.scopes.Presenter
import com.github.kiolk.alphabet.presentation.home.HomePresenter
import dagger.Subcomponent

@Presenter
@Subcomponent(modules = [HomePresenterModule::class])
interface HomePresenterComponent {

    val presenter: HomePresenter
}