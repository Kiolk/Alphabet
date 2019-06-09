package com.github.kiolk.alphabet.di.compomemts.presenters

import com.github.kiolk.alphabet.di.modules.presenter.ResultPresenterModule
import com.github.kiolk.alphabet.di.scopes.Presenter
import com.github.kiolk.alphabet.presentation.game.result.ResultPresenter
import dagger.Subcomponent

@Presenter
@Subcomponent(modules = [ResultPresenterModule::class])
interface ResultPresenterCompomemt{

    var presenter: ResultPresenter
}