package com.github.kiolk.alphabet.data.models.topic.local

import androidx.room.ColumnInfo
import com.github.kiolk.alphabet.data.models.word.Word.Companion.TAGS
import com.github.kiolk.alphabet.data.models.word.Word.Companion.WORD_IMAGE

data class TopicWithPhoto(@ColumnInfo(name = WORD_IMAGE)
                          val image: String,
                          @ColumnInfo(name = TAGS)
                          val topic: String)