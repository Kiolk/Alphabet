package com.github.kiolk.common.data.model.word

import androidx.room.ColumnInfo
import com.github.kiolk.common.data.model.word.Word.Companion.TAGS

data class TotalWordsTopic(
        @ColumnInfo(name = "count")
        val total: Int,
        @ColumnInfo(name = TAGS)
        val topic: String)