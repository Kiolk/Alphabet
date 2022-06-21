package com.github.kiolk.alphabet.data.models.game

import android.os.Parcelable
import com.github.kiolk.common.data.model.word.Word
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameItem(
    val currentWord: Word,
    val photoItems: List<Word>
) : Parcelable