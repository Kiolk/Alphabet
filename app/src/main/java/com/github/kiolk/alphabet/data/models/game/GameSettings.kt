package com.github.kiolk.alphabet.data.models.game

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
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
        var numberAskedWords: Int,
        @ColumnInfo(name = "isAvailable")
        var isAvailable: Boolean,
        @ColumnInfo(name = "isCompleted")
        var isCompleted: Boolean,
        @Embedded(prefix = "schema_")
        var gameSchema: GameSchema,
        @ColumnInfo(name = "level")
        val level: Int,
        @ColumnInfo(name = "stars")
        var stars: Int) : Parcelable

fun GameSettings.toBackupSettings(): BackupGameSettings {
    return BackupGameSettings(this.title, this.pictureUrl, this.queryRegex,
            this.secondQuery, this.thirdQuery, this.numberAskedWords,
            this.isAvailable, this.isCompleted, this.gameSchema, this.level, this.stars)
}