package com.github.kiolk.alphabet.di.modules

import com.github.kiolk.alphabet.data.source.words.remote.WordsService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NetworkServicesModule {

    @Singleton
    @Provides
    fun provideWordsService(retrofit: Retrofit): WordsService = retrofit.create(WordsService::class.java)
}