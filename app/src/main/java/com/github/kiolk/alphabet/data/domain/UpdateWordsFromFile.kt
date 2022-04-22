package com.github.kiolk.alphabet.data.domain

import com.github.kiolk.alphabet.data.source.words.RealWordsRepository
import com.github.kiolk.alphabet.utils.CsvParser
import com.github.kiolk.common.domain.base.UseCase
import io.reactivex.Completable
import javax.inject.Inject

class UpdateWordsFromFile
@Inject
constructor(private val realWordsRepository: RealWordsRepository) :
    UseCase<Completable, UpdateWordsFromFile.Params> {

    override fun execute(params: Params): Completable {
        val words = CsvParser.parserToWords(params.path)
        return realWordsRepository.setWordList(words)
    }

    data class Params(val path: String)
}