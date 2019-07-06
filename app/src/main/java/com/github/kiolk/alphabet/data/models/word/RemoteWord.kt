package com.github.kiolk.alphabet.data.models.word

import com.google.gson.annotations.SerializedName

data class RemoteWord(
        @SerializedName("value")
        val value: String,
        @SerializedName("syllables")
        val syllables: String,
        @SerializedName("image")
        val image: String,
        @SerializedName("tag")
        val tag: String)

fun RemoteWord.toWord(): Word{
        return Word(value, syllables, image, listOf(tag))
}