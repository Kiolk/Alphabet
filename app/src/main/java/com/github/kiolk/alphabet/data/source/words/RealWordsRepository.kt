package com.github.kiolk.alphabet.data.source.words

import com.github.kiolk.alphabet.data.models.game.GameSettings
import com.github.kiolk.alphabet.data.models.topic.Topic
import com.github.kiolk.alphabet.data.models.topic.local.TopicWithPhoto
import com.github.kiolk.alphabet.data.models.topic.local.TotalReadWordsTopic
import com.github.kiolk.alphabet.data.models.topic.local.TotalWordsTopic
import com.github.kiolk.alphabet.data.models.word.Mistake
import com.github.kiolk.alphabet.data.models.word.Word
import com.github.kiolk.alphabet.data.models.words.Words
import com.github.kiolk.common_di.qualifiers.LocalDataSource
import com.github.kiolk.common_di.qualifiers.RemoteDataSource
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class RealWordsRepository
@Inject
constructor(@LocalDataSource private val local: WordsDataSource,
            @RemoteDataSource private val remote: WordsDataSource) : WordsRepository {

    private var wordsCache: List<Word>? = null

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
        return local.selectWords(settings).map { words -> words.filter { word -> Word.checkDoubleLetter(word, settings) } }
    }

    override fun getWordsByTag(query: String): Flowable<List<Word>> {
        return local.getWordsByTags(query)
    }

    override fun isSettingsAvailable(gameSettings: GameSettings): Flowable<List<Word>> {
        return local.isSettingsAvailable(gameSettings).map { words -> words.filter { word -> Word.checkDoubleLetter(word, gameSettings) } }
    }

    override fun getAllDbWords(forceUpdate: Boolean): Flowable<List<Word>> {
//        return if(forceUpdate){
//            remote.getAllDbWords()
//        }else{
//            local.getAllDbWords()
//        }

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

    override fun getTopicWords(topic: Topic): Flowable<List<Word>> {
        return local.getTopicWords(topic)
    }

    override fun getRundomWordByLetter(letter: String): Single<Word> {
        if (wordsCache == null) {
            return local.getAllDbWords()
                    .map { words ->
                        wordsCache = words
                        Single.just(getRandomWord(letter))
                    }
                    .blockingFirst()
        }

        return Single.just(getRandomWord(letter))
    }

    private fun getRandomWord(letter: String): Word {
        var filteredWords = wordsCache?.filter { word -> word.value.first().toString() == letter && word.value.length <= 6 }
        if (filteredWords != null && filteredWords.isEmpty()) {
            filteredWords = wordsCache?.filter { word -> word.value.contains(letter) && word.value.length <= 6 }
        }

        return filteredWords?.random() ?: Word("Буквар", "Бук-вар", "", "")
    }

    override fun sendMistake(mistake: Mistake): Completable = remote.sendMistake(mistake)
}