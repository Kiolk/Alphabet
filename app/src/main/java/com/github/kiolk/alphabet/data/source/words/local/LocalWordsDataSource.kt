package com.github.kiolk.alphabet.data.source.words.local

import com.github.kiolk.alphabet.data.models.game.GameSettings
import com.github.kiolk.alphabet.data.models.topic.Topic
import com.github.kiolk.alphabet.data.models.topic.local.TopicWithPhoto
import com.github.kiolk.alphabet.data.models.topic.local.TotalReadWordsTopic
import com.github.kiolk.alphabet.data.models.topic.local.TotalWordsTopic
import com.github.kiolk.alphabet.data.models.word.Word
import com.github.kiolk.alphabet.data.models.words.Words
import com.github.kiolk.alphabet.data.source.words.WordsDataSource
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import java.lang.Exception
import java.lang.UnsupportedOperationException
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

    override fun selectWords(settings: GameSettings): Flowable<List<Word>> {
        return wordDao.getSelectedWord(settings.queryRegex, settings.secondQuery, settings.thirdQuery)
    }

    override fun getWordsByTags(query: String): Flowable<List<Word>> {
        return wordDao.getWordsByTag(query)
    }

    override fun isSettingsAvailable(gameSettings: GameSettings): Flowable<List<Word>> {
       return  wordDao.getSelectedBySettingsWords(gameSettings.queryRegex, gameSettings.secondQuery, gameSettings.thirdQuery)
    }

    override fun getAllDbWords(): Flowable<List<Word>> {
        throw UnsupportedOperationException()
    }

    override fun updateWord(word: Word): Completable {
        return Completable.create {
            try {
                wordDao.updateWord(word)
                it.onComplete()
            }catch (e: Exception){
                it.onError(e)
            }
        }
    }

    override fun getAvailableTopics(): Single<List<String>> {
        return wordDao.getAvailableTopic()
    }

    override fun countTotalWordsTopic(): Flowable<List<TotalWordsTopic>> {
        return wordDao.countTotalWordsTopic()
    }

    override fun countReadWordsTopic(): Flowable<List<TotalReadWordsTopic>> {
        return wordDao.countReadTotalWordsTopic()
    }

    override fun getTopicsWithPhoto(): Flowable<List<TopicWithPhoto>> {
        return wordDao.getTopicsWithImage()
    }

    override fun getTopicWords(topic: Topic): Flowable<List<Word>> {
        return wordDao.getTopicWords(topic.title)
    }
}