package com.github.kiolk.common.data.database.models.word

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.github.kiolk.common.data.database.converters.ListConverter
import kotlinx.parcelize.Parcelize

@Entity(tableName = "Word")
@TypeConverters(ListConverter::class)
@Parcelize
class WordDb(
    @PrimaryKey
    @ColumnInfo(name = WORD_MEAN)
    val value: String,
    @ColumnInfo(name = WORD_SYLLABLES)
    val syllables: String,
    @ColumnInfo(name = WORD_IMAGE)
    val image: String,
    @ColumnInfo(name = TAGS)
    val tags: String,
    @ColumnInfo(name = READ)
    var read: Int,
    @ColumnInfo(name = AUTHOR)
    val author: String,
    @ColumnInfo(name = ID)
    val id: String,
) : Parcelable {
    internal companion object {
        const val ID = "id"
        const val WORD_MEAN = "word_mean"
        const val TAGS = "tags"
        const val READ = "read"
        const val AUTHOR = "author"
        const val WORD_IMAGE = "word_image"
        const val WORD_SYLLABLES = "word_by_syllables"
    }
}
