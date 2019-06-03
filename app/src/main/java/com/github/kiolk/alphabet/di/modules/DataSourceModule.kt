package com.github.kiolk.alphabet.di.modules

import com.github.kiolk.alphabet.data.source.settings.RealSettingrepository
import com.github.kiolk.alphabet.data.source.settings.SettingsDataSource
import com.github.kiolk.alphabet.data.source.settings.SettingsRepository
import com.github.kiolk.alphabet.data.source.settings.local.LocalSettingsDataSource
import com.github.kiolk.alphabet.data.source.words.RealWordsRepository
import com.github.kiolk.alphabet.data.source.words.WordsDataSource
import com.github.kiolk.alphabet.data.source.words.WordsRepository
import com.github.kiolk.alphabet.data.source.words.local.LocalWordsDataSource
import com.github.kiolk.alphabet.di.qualifaiers.LocalDataSource
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DataSourceModule {

    @LocalDataSource
    @Singleton
    @Binds
    abstract fun provideLocalWordsDataSource(dataSource: LocalWordsDataSource) : WordsDataSource

    @Singleton
    @Binds
    abstract fun provideRealWordsRepository(repository : RealWordsRepository) : WordsRepository

    @LocalDataSource
    @Singleton
    @Binds
    abstract fun provideLocalSettingsDataSource(dataSource : LocalSettingsDataSource) : SettingsDataSource

    @Singleton
    @Binds
    abstract fun provideRealSettingsRepository(repository: RealSettingrepository) : SettingsRepository
}