package com.github.kiolk.alphabet.di.compomemts.presenters

import com.github.kiolk.alphabet.di.scopes.Presenter
import com.github.kiolk.alphabet.presentation.settings.SettingsPresenter
import dagger.Subcomponent

@Presenter
@Subcomponent
interface SettingsPresenterComponent {

    var presenter: SettingsPresenter
}