package com.github.kiolk.alphabet.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.kiolk.alphabet.data.models.player.Player
import com.github.kiolk.alphabet.data.source.levels.local.LevelDao
import com.github.kiolk.alphabet.data.source.player.local.PlayerDao
import com.github.kiolk.alphabet.data.source.settings.local.BackupSettingDao
import com.github.kiolk.alphabet.data.source.settings.local.SettingsDao
import com.github.kiolk.common.data.database.models.image.ImageDb
import com.github.kiolk.common.data.database.models.word.WordDb
import com.github.kiolk.common.data.model.level.LevelType
import com.github.kiolk.common.data.model.word.BackupGameSettings
import com.github.kiolk.common.data.model.word.GameSettings
import com.github.kiolk.common.data.model.word.Words
import com.github.kiolk.common.domain.repository.image.source.local.ImageDao
import com.github.kiolk.common.domain.repository.word.source.local.DaoWord
import com.github.kiolk.common.domain.repository.word.source.local.DaoWords

@Database(
    entities = [Words::class,
        WordDb::class,
        GameSettings::class, Player::class, LevelType::class, BackupGameSettings::class, ImageDb::class],
    version = 3,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun wordsDao(): DaoWords

    abstract fun wordDao(): DaoWord

    abstract fun settingsDao(): SettingsDao

    abstract fun playerDao(): PlayerDao

    abstract fun levelDao(): LevelDao

    abstract fun backupDao(): BackupSettingDao

    abstract fun imageDao(): ImageDao
}