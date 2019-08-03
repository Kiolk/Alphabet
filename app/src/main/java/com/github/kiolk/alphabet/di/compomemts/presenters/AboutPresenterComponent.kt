package com.github.kiolk.alphabet.di.compomemts.presenters

import com.github.kiolk.alphabet.di.scopes.Presenter
import com.github.kiolk.alphabet.presentation.settings.about.AboutPresenter
import dagger.Subcomponent

@Presenter
@Subcomponent
interface AboutPresenterComponent {

    var presenter: AboutPresenter
}