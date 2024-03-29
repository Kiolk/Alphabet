package com.github.kiolk.alphabet.di.modules

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.github.kiolk.alphabet.data.database.AppDatabase
import com.github.kiolk.alphabet.data.source.levels.local.LevelDao
import com.github.kiolk.alphabet.data.source.player.local.PlayerDao
import com.github.kiolk.alphabet.data.source.settings.local.BackupSettingDao
import com.github.kiolk.alphabet.data.source.settings.local.SettingsDao
import com.github.kiolk.alphabet.data.source.words.local.DaoWord
import com.github.kiolk.alphabet.data.source.words.local.DaoWords
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
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

    @Singleton
    @Provides
    fun providePlayerDao(database: AppDatabase): PlayerDao = database.playerDao()

    @Singleton
    @Provides
    fun provideLevelDao(database: AppDatabase): LevelDao = database.levelDao()

    @Singleton
    @Provides
    fun provideBackupSetting(database: AppDatabase): BackupSettingDao = database.backupDao()

    companion object {
        const val DATABASE_NAME: String = "words.db"
    }

    @Singleton
    @Provides
    fun provideFirebaseDataBase(): FirebaseDatabase {
        return Firebase.database
    }
}