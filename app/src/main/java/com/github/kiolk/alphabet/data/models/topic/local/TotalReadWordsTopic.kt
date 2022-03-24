package com.github.kiolk.alphabet.data.models.topic.local

import androidx.room.ColumnInfo
import com.github.kiolk.alphabet.data.models.word.Word.Companion.TAGS

data class TotalReadWordsTopic(@ColumnInfo(name = "count")
                               val read: Int,
                               @ColumnInfo(name = TAGS)
                               val topic: String)