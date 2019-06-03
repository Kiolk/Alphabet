package com.github.kiolk.alphabet.di.compomemts.presenters

import com.github.kiolk.alphabet.di.modules.presenter.GamePresenterModule
import com.github.kiolk.alphabet.di.scopes.Presenter
import com.github.kiolk.alphabet.presentation.game.game.GamePresenter
import dagger.Subcomponent


@Presenter
@Subcomponent(modules = [GamePresenterModule::class])
interface GamePresenterComponent {

    val presenter: GamePresenter
}