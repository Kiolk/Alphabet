package com.github.kiolk.alphabet.data.domain.words

import com.github.kiolk.common.data.model.word.Word
import com.github.kiolk.common.domain.base.UseCase
import com.github.kiolk.common.domain.repository.word.WordsRepository
import io.reactivex.Single
import javax.inject.Inject

class GetRandomWordUseCase
@Inject
constructor(private val wordsRepository: WordsRepository) :
    UseCase<Single<Word>, GetRandomWordUseCase.Params> {

    override fun execute(params: Params): Single<Word> {
        return wordsRepository.getRundomWordByLetter(params.letter)
    }

    data class Params(val letter: String)
}