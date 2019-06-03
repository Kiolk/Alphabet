package com.github.kiolk.alphabet.data.source.settings.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.github.kiolk.alphabet.data.models.game.GameSettings
import io.reactivex.Flowable

@Dao
interface SettingsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setSettings(settings : List<GameSettings>)

    @Query("SELECT * FROM Settings")
    fun getAllSettings() : Flowable<List<GameSettings>>
}