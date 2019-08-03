package com.github.kiolk.alphabet.di.compomemts

import com.github.kiolk.alphabet.di.compomemts.presenters.*
import com.github.kiolk.alphabet.di.modules.AppModule
import com.github.kiolk.alphabet.di.modules.DataSourceModule
import com.github.kiolk.alphabet.di.modules.DbModule
import com.github.kiolk.alphabet.di.modules.NetworkModule
import com.github.kiolk.alphabet.di.modules.presenter.GamePresenterModule
import com.github.kiolk.alphabet.di.modules.presenter.GamePreviewPresenterModule
import com.github.kiolk.alphabet.di.modules.presenter.HomePresenterModule
import com.github.kiolk.alphabet.di.modules.presenter.ResultPresenterModule
import com.github.kiolk.alphabet.presentation.settings.about.AboutPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,
    DataSourceModule::class,
    DbModule::class, NetworkModule::class])
interface ApplicationComponent {

    fun plusTaestClass(): TestClassComponent

    fun plusWordPresenter(): WordsPresenterComponent

    fun plusHomePresenter(module : HomePresenterModule): HomePresenterComponent

    fun plusGamePresenter(module : GamePresenterModule): GamePresenterComponent

    fun plusGamePreviewComponent(module: GamePreviewPresenterModule): GamePreviewComponent

    fun plusResultPresenter(module : ResultPresenterModule) : ResultPresenterCompomemt

    fun plusSplashPresenter() : SplashPresenterComponent

    fun plusMainPresenter(): MainPresenterComponent

    fun plusSettingsPresenter(): SettingsPresenterComponent

    fun plusAboutPresenter(): AboutPresenterComponent
}