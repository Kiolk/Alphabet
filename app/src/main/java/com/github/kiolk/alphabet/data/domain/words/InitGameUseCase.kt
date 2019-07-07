package com.github.kiolk.alphabet.data.domain.words

import com.github.kiolk.alphabet.data.domain.UseCase
import com.github.kiolk.alphabet.data.source.settings.SettingsRepository
import com.github.kiolk.alphabet.data.source.words.WordsRepository
import com.github.kiolk.alphabet.utils.Data.testSetOfWord
import io.reactivex.Completable
import javax.inject.Inject

class InitGameUseCase
@Inject
constructor(private val wordsRepository: WordsRepository) : UseCase<Completable, InitGameUseCase.Params> {

    override fun execute(params: Params): Completable {
//        return wordsRepository.setWordList(testSetOfWord)
        return wordsRepository.getAllDbWords().take(1).flatMapCompletable { words -> wordsRepository.setWordList(words.union(testSetOfWord).toList())}
    }

    class Params
}