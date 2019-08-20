package com.github.kiolk.alphabet.data.domain.words

import com.github.kiolk.alphabet.data.domain.UseCase
import com.github.kiolk.alphabet.data.models.player.Player
import com.github.kiolk.alphabet.data.source.player.PlayerRepository
import com.github.kiolk.alphabet.data.source.words.WordsRepository
import com.github.kiolk.alphabet.utils.Data.testSetOfWord
import io.reactivex.Completable
import javax.inject.Inject

class InitGameUseCase
@Inject
constructor(private val wordsRepository: WordsRepository,
            private val playerRepository: PlayerRepository) : UseCase<Completable, InitGameUseCase.Params> {

    override fun execute(params: Params): Completable {
        return wordsRepository.getAllDbWords().take(1).flatMapCompletable { words -> wordsRepository.setWordList(words.toList())}
                .andThen(playerRepository.addPlayer(Player("Main", 0)))
    }

    class Params
}