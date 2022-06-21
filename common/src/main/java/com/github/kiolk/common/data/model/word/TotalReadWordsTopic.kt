package com.github.kiolk.common.data.model.word

import androidx.room.ColumnInfo
import com.github.kiolk.common.data.model.word.Word.Companion.TAGS

data class TotalReadWordsTopic(@ColumnInfo(name = "count")
                               val read: Int,
                               @ColumnInfo(name = TAGS)
                               val topic: String)