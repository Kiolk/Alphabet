package com.github.kiolk.alphabet.di.modules.presenter

import com.github.kiolk.alphabet.data.models.letter.Letter
import com.github.kiolk.alphabet.di.scopes.Presenter
import dagger.Module
import dagger.Provides

@Module
class HomePresenterModule(private val letter : Letter) {

    @Presenter
    @Provides
    fun provideHomePresenterModule() : Letter = letter
}