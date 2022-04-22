package com.github.kiolk.alphabet.data.source.game

import com.github.kiolk.alphabet.data.models.game.GameSettings
import com.github.kiolk.common_di.qualifiers.LocalDataSource
import javax.inject.Inject

class RealGameRepository
@Inject
constructor(@LocalDataSource private val local: GameDataSourse) : GameRepository {

    override fun getNextGame(previeweGame: GameSettings): GameSettings {
        return local.getNextGame(previeweGame)
    }
}