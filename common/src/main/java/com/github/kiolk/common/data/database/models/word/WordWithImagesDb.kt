package com.github.kiolk.common.data.database.models.word

import androidx.room.ColumnInfo
import androidx.room.Relation
import com.github.kiolk.common.data.database.models.image.ImageDb
import com.github.kiolk.common.data.database.models.image.ImageDb.Companion.WORD_ID
import com.github.kiolk.common.data.database.models.word.WordDb.Companion.AUTHOR
import com.github.kiolk.common.data.database.models.word.WordDb.Companion.ID
import com.github.kiolk.common.data.database.models.word.WordDb.Companion.READ
import com.github.kiolk.common.data.database.models.word.WordDb.Companion.TAGS
import com.github.kiolk.common.data.database.models.word.WordDb.Companion.WORD_IMAGE
import com.github.kiolk.common.data.database.models.word.WordDb.Companion.WORD_MEAN
import com.github.kiolk.common.data.database.models.word.WordDb.Companion.WORD_SYLLABLES

class WordWithImagesDb(
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
    @Relation(parentColumn = ID, entityColumn = WORD_ID)
    val images: List<ImageDb>
)