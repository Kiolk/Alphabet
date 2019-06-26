package com.github.kiolk.alphabet.di.compomemts.presenters

import com.github.kiolk.alphabet.di.scopes.Presenter
import com.github.kiolk.alphabet.presentation.main.MainPresenter
import dagger.Subcomponent

@Presenter
@Subcomponent
interface MainPresenterComponent {

    var presenter: MainPresenter
}