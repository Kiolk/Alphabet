package com.github.kiolk.alphabet.data.source.levels.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.kiolk.common.data.model.level.LevelType

@Dao
interface LevelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addLevels(levels: List<LevelType>)

    @Query("SELECT * FROM level")
    fun getAllLevel(): List<LevelType>
}