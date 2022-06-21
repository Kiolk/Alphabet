package com.github.kiolk.alphabet.data.domain.words

import com.github.kiolk.alphabet.data.models.player.Player
import com.github.kiolk.alphabet.data.source.player.PlayerRepository
import com.github.kiolk.common.data.model.word.Word
import com.github.kiolk.common.domain.base.UseCase
import com.github.kiolk.common.domain.repository.image.ImageRepository
import com.github.kiolk.common.domain.repository.word.WordsRepository
import io.reactivex.Completable
import javax.inject.Inject

class InitGameDatabase @Inject constructor(
    private val wordsRepository: WordsRepository,
    private val playerRepository: PlayerRepository,
    private val imageRepository: ImageRepository,
) : UseCase<Completable, InitGameDatabase.Params> {

    override fun execute(params: Params): Completable {
        return wordsRepository.setWordList(params.words)
            .andThen(imageRepository.setImages(params.words.map { it.images }.flatten()))
            .andThen(playerRepository.addPlayer(Player(DEFAULT_USER_NAME, DEFAULT_NUMBER_STARS)))
    }

    class Params(val words: List<Word>)

    private companion object {
        const val DEFAULT_USER_NAME = "Main"
        const val DEFAULT_NUMBER_STARS = 0
    }
}