package com.github.kiolk.alphabet.data.source.levels.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.github.kiolk.alphabet.data.models.level.LevelType

@Dao
interface LevelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addLevels(levels: List<LevelType>)

    @Query("SELECT * FROM level")
    fun getAllLevel(): List<LevelType>
}