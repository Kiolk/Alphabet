package com.github.kiolk.common.domain.repository.word.source.local

import com.github.kiolk.common.data.model.image.Image
import com.github.kiolk.common.data.model.word.GameSettings
import com.github.kiolk.common.data.model.word.Topic
import com.github.kiolk.common.data.model.word.TopicWithPhoto
import com.github.kiolk.common.data.model.word.TotalReadWordsTopic
import com.github.kiolk.common.data.model.word.TotalWordsTopic
import com.github.kiolk.common.data.model.word.Word
import com.github.kiolk.common.data.model.word.Words
import com.github.kiolk.common.domain.repository.image.mapper.toDb
import com.github.kiolk.common.domain.repository.word.WordsDataSource
import com.github.kiolk.common.domain.repository.word.mapper.toDb
import com.github.kiolk.common.domain.repository.word.mapper.toDomain
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
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
                wordDao.insertAll(wordList.map { it.toDb() })
                it.onComplete()
            }catch (e : Exception){
                it.onError(e)
            }
        }
    }

    override fun selectWords(settings: GameSettings): Flowable<List<Word>> {
        return wordDao.getSelectedWord(
            settings.queryRegex,
            settings.secondQuery,
            settings.thirdQuery
        ).map { it.map { it.toDomain() } }
    }

    override fun getWordsByTags(query: String): Flowable<List<Word>> {
        return wordDao.getWordsByTag(query).map { it.map { it.toDomain() } }
    }

    override fun isSettingsAvailable(gameSettings: GameSettings): Flowable<List<Word>> {
        return wordDao.getSelectedBySettingsWords(
            gameSettings.queryRegex,
            gameSettings.secondQuery,
            gameSettings.thirdQuery
        ).map { it.map { it.toDomain() } }
    }

    override fun getAllDbWords(): Flowable<List<Word>> {
        return wordDao.getWordsWithImage().map { it.map { it.toDomain() } }
    }

    override fun updateWord(word: Word): Completable {
        return Completable.create {
            try {
                wordDao.updateWord(word.toDb())
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
        return wordDao.getWordsWithImage().map { it.map { it.toDomain() } }
    }

    override fun addImageToWord(word: Word, image: Image): Completable {
        TODO("Not yet implemented")
    }

    override fun getWordById(wordId: String): Single<Word> {
        TODO("Not yet implemented")
    }
}