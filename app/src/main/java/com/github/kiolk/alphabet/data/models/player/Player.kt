package com.github.kiolk.alphabet.data.models.player

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "player")
data class Player(
        @PrimaryKey
        @ColumnInfo(name = "player_name")
        val name: String,
        @ColumnInfo(name = "stars")
        var stars: Int
)