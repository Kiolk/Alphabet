package com.github.kiolk.alphabet.di.modules.presenter

import com.github.kiolk.alphabet.data.models.game.GameResult
import com.github.kiolk.alphabet.di.scopes.Presenter
import dagger.Module
import dagger.Provides

@Module
class ResultPresenterModule(private val result : GameResult) {

    @Presenter
    @Provides
    fun provideResultPresenterModule() : GameResult = result
}