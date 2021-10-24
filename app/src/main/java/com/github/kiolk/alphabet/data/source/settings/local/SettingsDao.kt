package com.github.kiolk.alphabet.data.source.settings.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.kiolk.alphabet.data.models.game.GameSettings
import io.reactivex.Flowable

@Dao
interface SettingsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setSettings(settings : List<GameSettings>)

    @Query("SELECT * FROM Settings")
    fun getAllSettings() : Flowable<List<GameSettings>>

    @Query("SELECT * FROM Settings WHERE schema_letterValue LIKE :letter ORDER BY level ASC")
    fun getSettingsByLetter(letter: String): Flowable<List<GameSettings>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateSAetting(gameSetting: GameSettings)

    @Query("SELECT * FROM Settings WHERE schema_letterValue LIKE :letter ORDER BY level ASC")
    fun getNextAvailableSettings(letter: String): List<GameSettings>


}