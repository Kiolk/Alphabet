package com.github.kiolk.alphabet.data.source.game

import com.github.kiolk.common.data.model.word.GameSettings

interface GameDataSourse {

    fun getNextGame(previeweGame: GameSettings): GameSettings
}