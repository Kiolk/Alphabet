package com.github.kiolk.alphabet.data.models.game

import android.arch.persistence.room.*
import android.os.Parcelable
import com.github.kiolk.alphabet.data.database.converters.ListConverter
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "Settings")
@TypeConverters(ListConverter::class)
@Parcelize
data class GameSettings(
        @PrimaryKey
        @ColumnInfo(name = "title")
        val title: String,
        @ColumnInfo(name = "setting_photo")
        val pictureUrl: String,
        @ColumnInfo(name = "first_query")
        val queryRegex: String,
        @ColumnInfo(name = "second_query")
        val secondQuery: String,
        @ColumnInfo(name = "third_query")
        val thirdQuery: String,
        @ColumnInfo(name = "number_asked")
        val numberAskedWords: Int,
        @ColumnInfo(name = "isAvailable")
        var isAvailable: Boolean,
        @ColumnInfo(name = "isCompleted")
        var isCompleted: Boolean,
        @Embedded(prefix = "schema_")
        var gameSchema: GameSchema) : Parcelable