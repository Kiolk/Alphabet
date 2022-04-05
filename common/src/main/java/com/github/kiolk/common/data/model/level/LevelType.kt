package com.github.kiolk.common.data.model.level

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "Level")
@Parcelize
class LevelType(
        @ColumnInfo(name = "image_id")
        val imageId: Int,
        @PrimaryKey
        @ColumnInfo(name = "name")
        val name: String,
        @ColumnInfo(name = "need_stars")
        val needStars: Int,
        @ColumnInfo(name = "sentence")
        val sentence: String,
        @ColumnInfo(name = "author")
        val author: String
) : Parcelable