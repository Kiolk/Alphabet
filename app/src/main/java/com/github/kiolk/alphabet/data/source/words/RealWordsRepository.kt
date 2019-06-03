package com.github.kiolk.alphabet.data.source.words

import com.github.kiolk.alphabet.data.models.word.Word
import com.github.kiolk.alphabet.data.models.words.Words
import com.github.kiolk.alphabet.di.qualifaiers.LocalDataSource
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class RealWordsRepository
@Inject
constructor(@LocalDataSource private val local: WordsDataSource) : WordsRepository {

    override fun getWordsSet(title: String): Flowable<List<String>> {
        return local.getWordsSet(title)
    }

    override fun setWordsSet(wordsSets: List<Words>): Completable {
        return local.setWordsSet(wordsSets)
    }

    override fun setWordList(wordList: List<Word>): Completable {
        return local.setWordList(wordList)
    }

    override fun selectWords(query: String): Flowable<List<Word>> {
        return local.selectWords(query)
    }

    override fun getWordsByTag(query: String): Flowable<List<Word>> {
        return local.getWordsByTags(query)
    }
}