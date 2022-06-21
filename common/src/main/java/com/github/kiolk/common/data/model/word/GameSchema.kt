package com.github.kiolk.common.data.model.word

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class GameSchema(
        @SerializedName("letters_number")
        var numberOfLetters: Int,
        @SerializedName("letter_value")
        var letterValue: String,
        @SerializedName("position")
        var position: Int,
        @SerializedName("word_length")
        var length: Int
): Parcelable