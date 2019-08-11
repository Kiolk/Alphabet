package com.github.kiolk.alphabet.di.compomemts.presenters

import com.github.kiolk.alphabet.di.scopes.Presenter
import com.github.kiolk.alphabet.presentation.settings.policy.PolicyPresenter
import dagger.Subcomponent


@Presenter
@Subcomponent
interface PolicyPresenterComponent {

    val presenter: PolicyPresenter
}