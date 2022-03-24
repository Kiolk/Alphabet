package com.github.kiolk.alphabet.data.models.game

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameStats(val asked: Int,
                     val correct: Int,
                     val stars: Int,
                     val isPreview: Boolean = false,
                     val isNext: Boolean = false): Parcelable