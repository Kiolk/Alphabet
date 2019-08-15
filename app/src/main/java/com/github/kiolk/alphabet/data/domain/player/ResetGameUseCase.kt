package com.github.kiolk.alphabet.data.domain.player

import com.github.kiolk.alphabet.data.domain.UseCase
import com.github.kiolk.alphabet.data.models.player.Player
import com.github.kiolk.alphabet.data.source.player.PlayerRepository
import com.github.kiolk.alphabet.data.source.settings.SettingsRepository
import com.github.kiolk.alphabet.data.source.words.WordsRepository
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class ResetGameUseCase
@Inject
constructor(private val wordsRepository: WordsRepository,
            private val playerRepository: PlayerRepository,
            private val settingsRepository: SettingsRepository) : UseCase<Completable, ResetGameUseCase.Params> {

    override fun execute(params: Params): Completable {
        return wordsRepository.getAllDbWords()
                .take(1)
                .map { words ->
                    words.forEach { word -> word.read = 0 }
                    return@map words
                }
                .flatMapCompletable { words ->
                    wordsRepository.setWordList(words)

                }
                .andThen(playerRepository.getPlayer("Main"))
                .flatMapCompletable { player -> playerRepository.updatePlayer(player.copy(stars = 0)) }
                .andThen(settingsRepository.getBackup())
                .flatMapCompletable { settings -> settingsRepository.setSettings(settings) }
    }

    class Params
}