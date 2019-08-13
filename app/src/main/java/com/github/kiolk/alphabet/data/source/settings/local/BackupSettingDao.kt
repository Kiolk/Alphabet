package com.github.kiolk.alphabet.data.source.settings.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.github.kiolk.alphabet.data.models.game.BackupGameSettings
import com.github.kiolk.alphabet.data.models.game.GameSettings
import io.reactivex.Single

@Dao
interface BackupSettingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setSettingBackup(settings: List<BackupGameSettings>)

    @Query("SELECT * FROM BackupSettings")
    fun getBackupSetting(): Single<List<BackupGameSettings>>
}