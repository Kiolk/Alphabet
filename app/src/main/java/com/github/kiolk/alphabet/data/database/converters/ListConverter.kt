package com.github.kiolk.alphabet.data.database.converters

import android.arch.persistence.room.TypeConverter

class ListConverter {

    @TypeConverter
    fun fromList(data : List<String>) : String = data.joinToString()

    @TypeConverter
    fun toList(resource : String) : List<String> = resource.split(',').filter { it.isEmpty() && !it.isBlank() }.map { it.trim() }.toList()
}