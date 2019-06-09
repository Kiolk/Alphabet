package com.github.kiolk.alphabet.data.source.game

import com.github.kiolk.alphabet.data.models.game.GameSettings

interface GameDataSourse {

    fun getNextGame(previeweGame: GameSettings): GameSettings
}