package com.github.kiolk.alphabet.di.compomemts.presenters

import com.github.kiolk.alphabet.di.scopes.Presenter
import com.github.kiolk.alphabet.presentation.settings.help.HelpPresenter
import dagger.Subcomponent

@Presenter
@Subcomponent
interface HelpPresenterComponent {

    val presenter: HelpPresenter
}