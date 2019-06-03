package com.github.kiolk.alphabet.data.models.word

import android.arch.persistence.room.*
import android.os.Parcelable
import com.github.kiolk.alphabet.data.database.converters.ListConverter
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "Word")
@TypeConverters(ListConverter::class)
@Parcelize
data class Word(
        @PrimaryKey
        @ColumnInfo(name = WORD_MEAN)
        val value: String,
        @ColumnInfo(name = "word_by_syllables")
        val syllables: String,
        @ColumnInfo(name = "word_image")
        val image: String,
        @ColumnInfo(name = TAGS)
        val tags: List<String>) : Parcelable{
        companion object {
            const val WORD_MEAN : String = "word_mean"
                const val TAGS : String = "tags"
        }
}