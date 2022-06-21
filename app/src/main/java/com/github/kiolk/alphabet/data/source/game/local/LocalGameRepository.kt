package com.github.kiolk.alphabet.data.source.game.local

import com.github.kiolk.alphabet.data.source.game.GameDataSourse
import com.github.kiolk.common.data.model.word.GameSettings
import javax.inject.Inject

class LocalGameRepository
@Inject
    constructor(): GameDataSourse {

    override fun getNextGame(previeweGame: GameSettings): GameSettings {
        throw UnsupportedOperationException()
    }
}