package com.github.kiolk.alphabet.data.models.game

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GameResult(
        val gameSettings: GameSettings,
        val gameItems: Array<GameItem>,
        var correctAnswers: Int,
        var wrongAnswers: Int) : Parcelable