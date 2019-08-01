package com.github.kiolk.alphabet.data.models.level

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

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