package com.github.kiolk.alphabet.data.domain

import com.github.kiolk.alphabet.data.models.game.GameItem
import com.github.kiolk.alphabet.data.models.game.GameSettings
import com.github.kiolk.alphabet.data.models.word.Word
import com.github.kiolk.alphabet.utils.randomize
import java.util.*

class PrepareGameSetUseCase : UseCase<List<GameItem>, PrepareGameSetUseCase.Params> {

    override fun execute(params: Params): List<GameItem> {
        val askedWords = params.words
                .sortedBy { it.read }
                .subList(0, params.askedWords)
                .toMutableList()
                .randomize()

        val gameSet = mutableListOf<GameItem>()

        for (word in askedWords) {
            val photoItems = mutableListOf<Word>()
            photoItems.add(word)

            while (photoItems.size != 4) {
                val image = params.words.get(Random().nextInt(params.words.size))
                if (!photoItems.contains(image)) {
                    photoItems.add(image)
                }
            }

            gameSet.add(GameItem(word, photoItems.randomize()))
        }

        return gameSet.randomize()
    }

    data class Params(val askedWords: Int, val words: List<Word>)
}