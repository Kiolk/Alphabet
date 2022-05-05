package com.github.kiolk.alphabet.data.domain.words

import com.github.kiolk.alphabet.data.domain.UseCase
import com.github.kiolk.alphabet.data.models.word.Word
import com.github.kiolk.alphabet.data.source.words.WordsRepository
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