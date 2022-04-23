package com.github.kiolk.alphabet.data.domain.words

import com.github.kiolk.alphabet.data.domain.UseCase
import com.github.kiolk.alphabet.data.models.player.Player
import com.github.kiolk.alphabet.data.models.word.Word
import com.github.kiolk.alphabet.data.source.player.PlayerRepository
import com.github.kiolk.alphabet.data.source.words.WordsRepository
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class InitGameDatabase @Inject constructor(
    private val wordsRepository: WordsRepository,
    private val playerRepository: PlayerRepository
) : UseCase<Completable, InitGameDatabase.Params> {

    override fun execute(params: Params): Completable {
        return Observable.just("")
            .flatMapCompletable { wordsRepository.setWordList(params.words) }
            .andThen(playerRepository.addPlayer(Player(DEFAULT_USER_NAME, DEFAULT_NUMBER_STARS)))
    }

    class Params(val words: List<Word>)

    private companion object {
        const val DEFAULT_USER_NAME = "Main"
        const val DEFAULT_NUMBER_STARS = 0
    }
}