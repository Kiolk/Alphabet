package com.github.kiolk.alphabet.data.models.words

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.github.kiolk.alphabet.data.database.converters.ListConverter

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