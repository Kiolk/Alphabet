package com.github.kiolk.common.data.database.models.image

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity(tableName = "Image")
@Parcelize
class ImageDb(
    @PrimaryKey
    @ColumnInfo(name = URL)
    val url: String,
    @ColumnInfo(name = AUTHOR)
    val author: String,
    @ColumnInfo(name = IS_REVIEWED)
    val isReviewed: Boolean,
    @ColumnInfo(name = WORD_ID)
    val wordId: String,
) : Parcelable {

    internal companion object {
        const val URL = "url"
        const val AUTHOR = "author"
        const val IS_REVIEWED = "is_reviewed"
        const val WORD_ID = "word_id"
    }
}