package com.github.kiolk.alphabet.data.source.words.local

import com.github.kiolk.alphabet.data.models.word.Word
import com.github.kiolk.alphabet.data.models.words.Words
import com.github.kiolk.alphabet.data.source.words.WordsDataSource
import io.reactivex.Completable
import io.reactivex.Flowable
import java.lang.Exception
import javax.inject.Inject

class LocalWordsDataSource
@Inject
constructor(private val dao : DaoWords,
            private val wordDao : DaoWord): WordsDataSource {
    override fun getWordsSet(title: String): Flowable<List<String>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setWordsSet(wordsSets: List<Words>): Completable {
        return Completable.create{
            try{
                dao.insertAll(wordsSets)
                it.onComplete()
            }catch (e : Exception){
                it.onError(e)
            }
        }
    }

    override fun setWordList(wordList: List<Word>): Completable {
        return Completable.create {
            try {
                wordDao.insertAll(wordList)
                it.onComplete()
            }catch (e : Exception){
                it.onError(e)
            }
        }
    }

    override fun selectWords(query: String): Flowable<List<Word>> {
        return wordDao.getSelectedWord(query, "%")
    }

    override fun getWordsByTags(query: String): Flowable<List<Word>> {
        return wordDao.getWordsByTag(query)
    }
}