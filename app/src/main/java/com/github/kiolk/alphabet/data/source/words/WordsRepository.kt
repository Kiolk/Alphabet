package com.github.kiolk.alphabet.data.source.words

import com.github.kiolk.alphabet.data.models.game.GameSettings
import com.github.kiolk.alphabet.data.models.topic.Topic
import com.github.kiolk.alphabet.data.models.topic.local.TopicWithPhoto
import com.github.kiolk.alphabet.data.models.topic.local.TotalReadWordsTopic
import com.github.kiolk.alphabet.data.models.topic.local.TotalWordsTopic
import com.github.kiolk.alphabet.data.models.word.Word
import com.github.kiolk.alphabet.data.models.words.Words
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface WordsRepository {

    fun getWordsSet(title: String): Flowable<List<String>>

    fun setWordsSet(wordsSets: List<Words>): Completable

    fun setWordList(wordList: List<Word>): Completable

    fun selectWords(gameSettings: GameSettings): Flowable<List<Word>>

    fun getWordsByTag(query: String): Flowable<List<Word>>

    fun isSettingsAvailable(gameSettings: GameSettings): Flowable<List<Word>>

    fun getAllDbWords(): Flowable<List<Word>>

    fun updateWord(word: Word): Completable

    fun getAvailableTopics(): Single<List<String>>

    fun countTotalWordsTopic(): Flowable<List<TotalWordsTopic>>

    fun countReadWordsTopic(): Flowable<List<TotalReadWordsTopic>>

    fun getTopicsWithPhoto(): Flowable<List<TopicWithPhoto>>

    fun getTopicWords(topic: Topic): Flowable<List<Word>>
}