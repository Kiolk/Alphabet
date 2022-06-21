package com.github.kiolk.common.data.model.word

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.github.kiolk.common.data.database.converters.ListConverter
import com.github.kiolk.common.data.model.word.GameSchema
import kotlinx.parcelize.Parcelize

@Entity(tableName = "BackupSettings")
@TypeConverters(ListConverter::class)
@Parcelize
data class BackupGameSettings(
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

fun BackupGameSettings.toGameSattings(): GameSettings{
        return GameSettings(this.title, this.pictureUrl, this.queryRegex,
                this.secondQuery, this.thirdQuery, this.numberAskedWords,
                this.isAvailable, this.isCompleted, this.gameSchema, this.level, this.stars)
}