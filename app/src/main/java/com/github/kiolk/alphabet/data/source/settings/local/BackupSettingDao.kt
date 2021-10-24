package com.github.kiolk.alphabet.data.source.settings.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.kiolk.alphabet.data.models.game.BackupGameSettings
import io.reactivex.Single

@Dao
interface BackupSettingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setSettingBackup(settings: List<BackupGameSettings>)

    @Query("SELECT * FROM BackupSettings")
    fun getBackupSetting(): Single<List<BackupGameSettings>>
}