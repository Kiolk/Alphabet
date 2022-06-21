package com.github.kiolk.alphabet.data.domain.words

import com.github.kiolk.common.data.model.word.Word
import com.github.kiolk.common.domain.base.UseCase
import com.github.kiolk.common.domain.repository.word.WordsRepository
import io.reactivex.Flowable
import javax.inject.Inject

class InitGameUseCase
@Inject
constructor(private val wordsRepository: WordsRepository) :
    UseCase<Flowable<List<Word>>, InitGameUseCase.Params> {

    override fun execute(params: Params): Flowable<List<Word>> {
        return wordsRepository.getAllDbWords()
    }

    class Params
}