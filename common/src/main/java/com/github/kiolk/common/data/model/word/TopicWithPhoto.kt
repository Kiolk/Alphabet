package com.github.kiolk.common.data.model.word

import androidx.room.ColumnInfo
import com.github.kiolk.common.data.model.word.Word.Companion.TAGS
import com.github.kiolk.common.data.model.word.Word.Companion.WORD_IMAGE

data class TopicWithPhoto(@ColumnInfo(name = WORD_IMAGE)
                          val image: String,
                          @ColumnInfo(name = TAGS)
                          val topic: String)