package com.github.kiolk.alphabet.data.source.words.remote

import com.github.kiolk.alphabet.data.models.game.GameSettings
import com.github.kiolk.alphabet.data.models.topic.Topic
import com.github.kiolk.alphabet.data.models.topic.local.TopicWithPhoto
import com.github.kiolk.alphabet.data.models.topic.local.TotalReadWordsTopic
import com.github.kiolk.alphabet.data.models.topic.local.TotalWordsTopic
import com.github.kiolk.alphabet.data.models.word.Mistake
import com.github.kiolk.alphabet.data.models.word.Word
import com.github.kiolk.alphabet.data.models.word.toWord
import com.github.kiolk.alphabet.data.models.words.Words
import com.github.kiolk.alphabet.data.source.words.WordsDataSource
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import java.lang.UnsupportedOperationException
import javax.inject.Inject

class RemoteWordsDataSource
@Inject
constructor (private val service: WordsService) : WordsDataSource {

    override fun getWordsSet(title: String): Flowable<List<String>> {
        throw UnsupportedOperationException()
    }

    override fun setWordsSet(wordsSets: List<Words>): Completable {
        throw UnsupportedOperationException()
    }

    override fun setWordList(wordList: List<Word>): Completable {
        throw UnsupportedOperationException()
    }

    override fun selectWords(settings: GameSettings): Flowable<List<Word>> {
        throw UnsupportedOperationException()
    }

    override fun getWordsByTags(query: String): Flowable<List<Word>> {
        throw UnsupportedOperationException()
    }

    override fun isSettingsAvailable(gameSettings: GameSettings): Flowable<List<Word>> {
        throw UnsupportedOperationException()
    }

    override fun getAllDbWords(): Flowable<List<Word>> {
        return service.getWords().map { words -> return@map words.map { it.toWord() } }
    }

    override fun updateWord(word: Word): Completable {
        throw UnsupportedOperationException()
    }

    override fun getAvailableTopics(): Single<List<String>> {
        throw UnsupportedOperationException()
    }

    override fun countTotalWordsTopic(): Flowable<List<TotalWordsTopic>> {
        throw UnsupportedOperationException()
    }

    override fun countReadWordsTopic(): Flowable<List<TotalReadWordsTopic>> {
        throw UnsupportedOperationException()
    }

    override fun getTopicsWithPhoto(): Flowable<List<TopicWithPhoto>> {
        throw UnsupportedOperationException()
    }

    override fun getTopicWords(topic: Topic): Flowable<List<Word>> {
        throw UnsupportedOperationException()
    }

    override fun sendMistake(mistake: Mistake): Completable {
        return service.sendMistake(mistake)
    }
}