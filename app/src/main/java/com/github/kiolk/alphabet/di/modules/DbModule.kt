package com.github.kiolk.alphabet.di.modules

import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.github.kiolk.alphabet.data.database.AppDatabase
import com.github.kiolk.alphabet.data.source.settings.local.SettingsDao
import com.github.kiolk.alphabet.data.source.words.local.DaoWord
import com.github.kiolk.alphabet.data.source.words.local.DaoWords
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context) : AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME )
            .fallbackToDestructiveMigration()
            .setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
            .build()

    @Singleton
    @Provides
    fun rovideWordsDao(database : AppDatabase) : DaoWords = database.wordsDao()

    @Singleton
    @Provides
    fun provideWordDao(database: AppDatabase) : DaoWord = database.wordDao()

    @Singleton
    @Provides
    fun provideSettingsDao(database: AppDatabase) : SettingsDao = database.settingsDao()

    companion object {
        const val DATABASE_NAME : String = "words.db"
    }
}