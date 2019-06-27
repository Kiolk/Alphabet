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

    @Named(WORDS_TAG)
    @Singleton
    @Provides
    fun provideWords() : Array<String> = context.resources.getStringArray(R.array.words)

    @Named(SYLLABLES_TAG)
    @Singleton
    @Provides
    fun provideSyllables() : Array<String> = context.resources.getStringArray(R.array.syllable)

    @Named(THREE_LATTERS_WORD)
    @Singleton
    @Provides
    fun provideThreeLattersWords() : Array<String> = context.resources.getStringArray(R.array.short_words)

    @Named(FOR_CHAR_WORDS)
    @Singleton
    @Provides
    fun provideForCharWords() : Array<String> = context.resources.getStringArray(R.array.forCahrWords)

    @Named(ONE_FROM)
    @Singleton
    @Provides
    fun provideOneFrom() : Array<String> = context.resources.getStringArray(R.array.fourCharOne)

    @Named(FIVE_CHAR)
    @Singleton
    @Provides
    fun provideFiveChar() : Array<String> = context.resources.getStringArray(R.array.fiveChar)

    @Named(SIX_CHAR)
    @Singleton
    @Provides
    fun provideSixChar() : Array<String> = context.resources.getStringArray(R.array.sixChar)

    companion object {
        const val WORDS_TAG : String = "WORDS_SET"
        const val SYLLABLES_TAG : String = "SYLLABLES_TAG"
        const val THREE_LATTERS_WORD : String = "THREE_LATTERS_WORD"
        const val FOR_CHAR_WORDS : String = "FOR_CHAR_WORDS"
        const val ONE_FROM : String = "ONE_FROM"
        const val FIVE_CHAR : String = "FIVE_CHAR"
        const val SIX_CHAR : String = "SIX_CHAR"
    }
}