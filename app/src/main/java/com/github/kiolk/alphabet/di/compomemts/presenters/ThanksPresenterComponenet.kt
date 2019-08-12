package com.github.kiolk.alphabet.di.compomemts.presenters

import com.github.kiolk.alphabet.di.scopes.Presenter
import com.github.kiolk.alphabet.presentation.settings.thanks.ThanksPresenter
import dagger.Subcomponent

@Presenter
@Subcomponent
interface ThanksPresenterComponenet {

    val presenter: ThanksPresenter
}