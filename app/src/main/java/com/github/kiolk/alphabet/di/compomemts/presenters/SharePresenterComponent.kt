package com.github.kiolk.alphabet.di.compomemts.presenters

import com.github.kiolk.alphabet.di.scopes.Presenter
import com.github.kiolk.alphabet.presentation.settings.share.SharePresenter
import dagger.Subcomponent

@Presenter
@Subcomponent
interface SharePresenterComponent {

    var presenter: SharePresenter
}