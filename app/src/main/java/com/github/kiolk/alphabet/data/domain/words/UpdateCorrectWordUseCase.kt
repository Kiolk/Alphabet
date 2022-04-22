package com.github.kiolk.alphabet.data.domain.words

import com.github.kiolk.alphabet.data.models.word.Word
import com.github.kiolk.alphabet.data.source.words.WordsRepository
import com.github.kiolk.common.domain.base.UseCase
import io.reactivex.Completable
import javax.inject.Inject

class UpdateCorrectWordUseCase
@Inject
constructor(val wordsRepository: WordsRepository) :
    UseCase<Completable, UpdateCorrectWordUseCase.Params> {

    override fun execute(params: Params): Completable {
        ++params.word.read
        return wordsRepository.updateWord(params.word)
    }

    data class Params(val word: Word)
}