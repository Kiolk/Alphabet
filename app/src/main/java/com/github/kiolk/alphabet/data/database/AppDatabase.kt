package com.github.kiolk.alphabet.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.github.kiolk.alphabet.data.models.game.GameSettings
import com.github.kiolk.alphabet.data.models.word.Word
import com.github.kiolk.alphabet.data.models.words.Words
import com.github.kiolk.alphabet.data.source.settings.local.SettingsDao
import com.github.kiolk.alphabet.data.source.words.local.DaoWord
import com.github.kiolk.alphabet.data.source.words.local.DaoWords

@Database(entities = arrayOf(
        Words::class, Word::class, GameSettings::class
), version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun wordsDao(): DaoWords

    abstract fun wordDao() : DaoWord

    abstract fun settingsDao() : SettingsDao
}