package com.github.kiolk.alphabet.di.modules.presenter

import com.github.kiolk.alphabet.data.models.game.GameSettings
import com.github.kiolk.alphabet.di.scopes.Presenter
import dagger.Module
import dagger.Provides

@Module
class GamePresenterModule(private val gameSettings: GameSettings) {

    @Presenter
    @Provides
    fun provideGamePresenterModule(): GameSettings = gameSettings
}