package com.github.kiolk.alphabet.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.kiolk.alphabet.data.models.game.BackupGameSettings
import com.github.kiolk.alphabet.data.models.game.GameSettings
import com.github.kiolk.alphabet.data.models.player.Player
import com.github.kiolk.alphabet.data.models.word.Word
import com.github.kiolk.alphabet.data.models.words.Words
import com.github.kiolk.alphabet.data.source.levels.local.LevelDao
import com.github.kiolk.alphabet.data.source.player.local.PlayerDao
import com.github.kiolk.alphabet.data.source.settings.local.BackupSettingDao
import com.github.kiolk.alphabet.data.source.settings.local.SettingsDao
import com.github.kiolk.alphabet.data.source.words.local.DaoWord
import com.github.kiolk.alphabet.data.source.words.local.DaoWords
import com.github.kiolk.common.data.model.level.LevelType

@Database(entities = arrayOf(
        Words::class, Word::class, GameSettings::class, Player::class, LevelType::class, BackupGameSettings::class
), version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun wordsDao(): DaoWords

    abstract fun wordDao() : DaoWord

    abstract fun settingsDao() : SettingsDao

    abstract fun playerDao(): PlayerDao

    abstract fun levelDao(): LevelDao

    abstract fun backupDao(): BackupSettingDao
}