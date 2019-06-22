package com.github.kiolk.alphabet.data.domain.words

import com.github.kiolk.alphabet.data.domain.UseCase
import com.github.kiolk.alphabet.data.source.settings.SettingsRepository
import com.github.kiolk.alphabet.data.source.words.WordsRepository
import com.github.kiolk.alphabet.utils.Data.testSetOfWord
import io.reactivex.Completable
import javax.inject.Inject

class InitGameUseCase
@Inject
constructor(private val wordsRepository: WordsRepository,
            private val settingsRepository: SettingsRepository) : UseCase<Completable, InitGameUseCase.Params> {

    override fun execute(params: Params): Completable {
        return wordsRepository.setWordList(testSetOfWord)
//                .concatWith {
//                            val allSettings: MutableList<GameSettings> = mutableListOf()
//                            for (letter in alphabet) {
//                                testSettings.forEach { allSettings.add(GameSettings(it.title, it.pictureUrl, it.queryRegex, "%${letter.letterValue}%", it.thirdQuery, 4, false, false)) }
//                            }
//
//                           return settingsRepository.setSettings(allSettings.filter {
//                               val words = wordsRepository.isSettingsAvailable(it)
//                                words.size > 3
//
//                            })
//                }

    }

    class Params
}