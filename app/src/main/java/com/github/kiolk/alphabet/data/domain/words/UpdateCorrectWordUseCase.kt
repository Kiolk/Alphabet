package com.github.kiolk.alphabet.data.domain.words

import com.github.kiolk.common.data.model.word.Word
import com.github.kiolk.common.domain.base.UseCase
import com.github.kiolk.common.domain.repository.word.WordsRepository
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