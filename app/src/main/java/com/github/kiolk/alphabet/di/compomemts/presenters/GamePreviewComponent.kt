package com.github.kiolk.alphabet.di.compomemts.presenters

import com.github.kiolk.alphabet.di.modules.presenter.GamePresenterModule
import com.github.kiolk.alphabet.di.modules.presenter.GamePreviewPresenterModule
import com.github.kiolk.alphabet.di.scopes.Presenter
import com.github.kiolk.alphabet.presentation.game.preview.GamePreviewPresenter
import dagger.Subcomponent

@Presenter
@Subcomponent(modules = [GamePreviewPresenterModule::class])
interface GamePreviewComponent {

    var presenter: GamePreviewPresenter
}