package com.github.kiolk.alphabet.data.domain.words

import com.github.kiolk.alphabet.data.source.words.WordsRepository
import com.github.kiolk.common.domain.base.UseCase
import io.reactivex.Single
import javax.inject.Inject

class GetAllImageAuthorsUseCase
@Inject
constructor(private val wordsRepository: WordsRepository) :
    UseCase<Single<List<String>>, GetAllImageAuthorsUseCase.Params> {

    override fun execute(params: Params): Single<List<String>> {
        return wordsRepository.getAllDbWords()
            .map { words ->
                return@map words.map { word -> word.author }
            }
            .map { author -> author.toSet().toList() }
            .take(1)
            .singleOrError()
    }

    class Params
}