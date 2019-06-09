package com.github.kiolk.alphabet.data.models.game

import android.os.Parcelable
import com.github.kiolk.alphabet.data.models.word.Word
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GameItem(
        val currentWord : Word,
        val photoItems : List<Word>) : Parcelable