package com.github.kiolk.alphabet.di.modules

import com.github.kiolk.alphabet.data.source.game.GameDataSourse
import com.github.kiolk.alphabet.data.source.game.GameRepository
import com.github.kiolk.alphabet.data.source.game.RealGameRepository
import com.github.kiolk.alphabet.data.source.game.local.LocalGameRepository
import com.github.kiolk.alphabet.data.source.player.PlayerDataSource
import com.github.kiolk.alphabet.data.source.player.PlayerRepository
import com.github.kiolk.alphabet.data.source.player.RealPlayerrepository
import com.github.kiolk.alphabet.data.source.player.local.LocalPlayerDataSource
import com.github.kiolk.alphabet.data.source.settings.RealSettingrepository
import com.github.kiolk.alphabet.data.source.settings.SettingsDataSource
import com.github.kiolk.alphabet.data.source.settings.SettingsRepository
import com.github.kiolk.alphabet.data.source.settings.local.LocalSettingsDataSource
import com.github.kiolk.alphabet.data.source.words.RealWordsRepository
import com.github.kiolk.alphabet.data.source.words.WordsDataSource
import com.github.kiolk.alphabet.data.source.words.WordsRepository
import com.github.kiolk.alphabet.data.source.words.local.LocalWordsDataSource
import com.github.kiolk.alphabet.data.source.words.remote.RemoteWordsDataSource
import com.github.kiolk.alphabet.di.qualifaiers.LocalDataSource
import com.github.kiolk.alphabet.di.qualifaiers.RemoteDataSource
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DataSourceModule {

    @LocalDataSource
    @Singleton
    @Binds
    abstract fun provideLocalWordsDataSource(dataSource: LocalWordsDataSource): WordsDataSource

    @RemoteDataSource
    @Singleton
    @Binds
    abstract fun provideRemoteWordsDataSource(dataSource: RemoteWordsDataSource): WordsDataSource

    @Singleton
    @Binds
    abstract fun provideRealWordsRepository(repository: RealWordsRepository): WordsRepository

    @LocalDataSource
    @Singleton
    @Binds
    abstract fun provideLocalSettingsDataSource(dataSource: LocalSettingsDataSource): SettingsDataSource

    @Singleton
    @Binds
    abstract fun provideRealSettingsRepository(repository: RealSettingrepository): SettingsRepository

    @Singleton
    @Binds
    abstract fun providGmaeSettings(repository: RealGameRepository): GameRepository

    @LocalDataSource
    @Singleton
    @Binds
    abstract fun provideGameResult(datatasource: LocalGameRepository) : GameDataSourse

    @Singleton
    @Binds
    abstract fun provideRealPlayerDataSource(dataSource: LocalPlayerDataSource): PlayerDataSource

    @Singleton
    @Binds
    abstract fun provideRealPlayerRepository(repository: RealPlayerrepository): PlayerRepository
}