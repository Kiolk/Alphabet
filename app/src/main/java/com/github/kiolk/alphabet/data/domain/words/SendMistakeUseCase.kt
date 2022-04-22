package com.github.kiolk.alphabet.data.domain.words

import com.github.kiolk.alphabet.data.models.word.Mistake
import com.github.kiolk.alphabet.data.source.words.WordsRepository
import com.github.kiolk.common.domain.base.UseCase
import io.reactivex.Completable
import javax.inject.Inject

class SendMistakeUseCase
@Inject
constructor(private val wordsRepository: WordsRepository) :
    UseCase<Completable, SendMistakeUseCase.Params> {

    override fun execute(params: Params): Completable {
        return wordsRepository.sendMistake(Mistake(params.word, params.description))
    }

    data class Params(
        val word: String,
        val description: String
    )
}