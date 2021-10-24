package com.github.kiolk.alphabet.data.models.game

import android.os.Parcelable
import com.github.kiolk.alphabet.data.models.topic.Topic
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameResult(
        val gameSettings: GameSettings? = null,
        val topic: Topic? = null,
        val gameItems: Array<GameItem>,
        var correctAnswers: Int,
        var wrongAnswers: Int) : Parcelable