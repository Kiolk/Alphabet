package com.github.kiolk.alphabet.di.compomemts.presenters

import com.github.kiolk.alphabet.di.scopes.Presenter
import com.github.kiolk.alphabet.presentation.game.preview.GamePreviewPresenter
import dagger.Subcomponent

@Presenter
@Subcomponent
interface GamePreviewComponenet {

    var presenter: GamePreviewPresenter
}