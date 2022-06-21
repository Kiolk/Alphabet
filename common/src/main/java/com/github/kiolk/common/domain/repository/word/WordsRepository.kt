package com.github.kiolk.common.domain.repository.word

import com.github.kiolk.common.data.model.image.Image
import com.github.kiolk.common.data.model.word.Word
import com.github.kiolk.common.data.model.word.GameSettings
import com.github.kiolk.common.data.model.word.Mistake
import com.github.kiolk.common.data.model.word.Topic
import com.github.kiolk.common.data.model.word.TopicWithPhoto
import com.github.kiolk.common.data.model.word.TotalReadWordsTopic
import com.github.kiolk.common.data.model.word.TotalWordsTopic
import com.github.kiolk.common.data.model.word.Words
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

    fun getAllDbWords(forceUpdate: Boolean = true): Flowable<List<Word>>

    fun updateWord(word: Word): Completable

    fun getAvailableTopics(): Single<List<String>>

    fun countTotalWordsTopic(): Flowable<List<TotalWordsTopic>>

    fun countReadWordsTopic(): Flowable<List<TotalReadWordsTopic>>

    fun getTopicsWithPhoto(): Flowable<List<TopicWithPhoto>>

    fun getTopicWords(topic: Topic): Flowable<List<Word>>

    fun getRundomWordByLetter(letter: String): Single<Word>

    fun sendMistake(mistake: Mistake): Completable

    fun addImageToWord(word: Word, image: Image): Completable

    fun getWordById(wordId: String): Single<Word>
}