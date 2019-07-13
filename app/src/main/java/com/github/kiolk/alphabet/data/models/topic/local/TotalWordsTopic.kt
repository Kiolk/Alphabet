package com.github.kiolk.alphabet.data.models.topic.local

import android.arch.persistence.room.ColumnInfo
import com.github.kiolk.alphabet.data.models.word.Word.Companion.TAGS

data class TotalWordsTopic(
        @ColumnInfo(name = "count")
        val total: Int,
        @ColumnInfo(name = TAGS)
        val topic: String)