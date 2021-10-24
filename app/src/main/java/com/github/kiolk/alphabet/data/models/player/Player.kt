package com.github.kiolk.alphabet.data.models.player

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "player")
data class Player(
        @PrimaryKey
        @ColumnInfo(name = "player_name")
        val name: String,
        @ColumnInfo(name = "stars")
        var stars: Int
)