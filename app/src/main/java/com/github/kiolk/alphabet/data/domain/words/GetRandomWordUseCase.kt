package com.github.kiolk.alphabet.data.domain.words

import com.github.kiolk.alphabet.data.models.word.Word
import com.github.kiolk.alphabet.data.source.words.WordsRepository
import com.github.kiolk.common.domain.base.UseCase
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