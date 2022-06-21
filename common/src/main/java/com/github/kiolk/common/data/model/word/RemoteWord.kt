package com.github.kiolk.common.data.model.word

import com.github.kiolk.common.data.model.word.Word
import com.google.gson.annotations.SerializedName

data class RemoteWord(
        @SerializedName("value")
        val value: String,
        @SerializedName("syllables")
        val syllables: String,
        @SerializedName("image")
        val image: String,
        @SerializedName("tag")
        val tag: String,
        @SerializedName("image_author")
        val author: String?)

fun RemoteWord.toWord(): Word {
    return Word(value, syllables, image, tag, 0, author ?: "")
}