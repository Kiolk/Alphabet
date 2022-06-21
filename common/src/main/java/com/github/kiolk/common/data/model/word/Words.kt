package com.github.kiolk.common.data.model.word

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.github.kiolk.common.data.database.converters.ListConverter

@Entity(tableName = "Words")
@TypeConverters(ListConverter::class)
data class Words(
        @PrimaryKey
        @ColumnInfo(name = "title")
        val title: String,
        @ColumnInfo(name = "iamge")
        val image: String,
        @ColumnInfo(name = "words")
        val words: List<String>)