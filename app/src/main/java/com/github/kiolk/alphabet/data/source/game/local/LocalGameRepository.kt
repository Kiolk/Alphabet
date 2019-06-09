package com.github.kiolk.alphabet.data.source.game.local

import com.github.kiolk.alphabet.data.models.game.GameSettings
import com.github.kiolk.alphabet.data.source.game.GameDataSourse
import java.lang.UnsupportedOperationException
import javax.inject.Inject

class LocalGameRepository
@Inject
    constructor(): GameDataSourse {

    override fun getNextGame(previeweGame: GameSettings): GameSettings {
        throw UnsupportedOperationException()
    }
}