package com.github.kiolk.alphabet.data.models.game

import android.os.Parcelable
import com.github.kiolk.common.data.model.word.GameSettings
import com.github.kiolk.common.data.model.word.Topic
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameResult(
        val gameSettings: GameSettings? = null,
        val topic: Topic? = null,
        val gameItems: Array<GameItem>,
        var correctAnswers: Int,
        var wrongAnswers: Int) : Parcelable