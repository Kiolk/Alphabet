package com.github.kiolk.alphabet.di.modules.presenter

import com.github.kiolk.alphabet.di.scopes.Presenter
import com.github.kiolk.common.data.model.word.GameSettings
import dagger.Module
import dagger.Provides

@Module
class GamePreviewPresenterModule(private val gameSettings: GameSettings){

    @Presenter
    @Provides
    fun provideGamePreviewPresenterModule(): GameSettings = gameSettings
}