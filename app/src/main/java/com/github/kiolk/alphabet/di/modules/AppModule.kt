package com.github.kiolk.alphabet.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.data.SoundManager
import com.github.kiolk.alphabet.data.domain.PrepareGameSetUseCase
import com.github.kiolk.alphabet.utils.RxSchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule(context : Context) {

    private val context : Context by lazy { context.applicationContext }

    @Singleton
    @Provides
    fun provideContext() : Context = context

    @Singleton
    @Provides
    fun provideRxSchedulerProvider() : RxSchedulerProvider = RxSchedulerProvider()

    @Singleton
    @Provides
    fun providePrepareUseCase() : PrepareGameSetUseCase = PrepareGameSetUseCase()

    @Singleton
    @Provides
    fun provideSoundPull(context : Context) : SoundManager  = SoundManager(context)

    @Singleton
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences = context.getSharedPreferences("GameSettings", Context.MODE_PRIVATE)

}