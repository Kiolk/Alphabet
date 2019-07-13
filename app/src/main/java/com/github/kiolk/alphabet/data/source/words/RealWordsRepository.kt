package com.github.kiolk.alphabet.data.source.words

import com.github.kiolk.alphabet.data.models.game.GameSettings
import com.github.kiolk.alphabet.data.models.topic.local.TopicWithPhoto
import com.github.kiolk.alphabet.data.models.topic.local.TotalReadWordsTopic
import com.github.kiolk.alphabet.data.models.topic.local.TotalWordsTopic
import com.github.kiolk.alphabet.data.models.word.Word
import com.github.kiolk.alphabet.data.models.words.Words
import com.github.kiolk.alphabet.di.qualifaiers.LocalDataSource
import com.github.kiolk.alphabet.di.qualifaiers.RemoteDataSource
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import org.intellij.lang.annotations.Flow
import javax.inject.Inject

class RealWordsRepository
@Inject
constructor(@LocalDataSource private val local: WordsDataSource,
            @RemoteDataSource private val remote: WordsDataSource) : WordsRepository {

    override fun getWordsSet(title: String): Flowable<List<String>> {
        return local.getWordsSet(title)
    }

    override fun setWordsSet(wordsSets: List<Words>): Completable {
        return local.setWordsSet(wordsSets)
    }

    override fun setWordList(wordList: List<Word>): Completable {
        return local.setWordList(wordList)
    }

    override fun selectWords(settings: GameSettings): Flowable<List<Word>> {
        return local.selectWords(settings)
    }

    override fun getWordsByTag(query: String): Flowable<List<Word>> {
        return local.getWordsByTags(query)
    }

    override fun isSettingsAvailable(gameSettings: GameSettings): Flowable<List<Word>> {
        return local.isSettingsAvailable(gameSettings)
    }

    override fun getAllDbWords(): Flowable<List<Word>> {
        return remote.getAllDbWords()
    }

    override fun updateWord(word: Word): Completable {
        return local.updateWord(word)
    }

    override fun getAvailableTopics(): Single<List<String>> {
        return local.getAvailableTopics()
    }

    override fun countTotalWordsTopic(): Flowable<List<TotalWordsTopic>> {
        return local.countTotalWordsTopic()
    }

    override fun countReadWordsTopic(): Flowable<List<TotalReadWordsTopic>> {
        return local.countReadWordsTopic()
    }

    override fun getTopicsWithPhoto(): Flowable<List<TopicWithPhoto>> {
        return local.getTopicsWithPhoto()
    }
}