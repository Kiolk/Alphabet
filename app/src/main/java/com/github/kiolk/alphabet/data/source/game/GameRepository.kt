package com.github.kiolk.alphabet.data.source.game

import com.github.kiolk.alphabet.data.models.game.GameSettings

interface GameRepository {

    fun getNextGame(previeweGame: GameSettings): GameSettings
}