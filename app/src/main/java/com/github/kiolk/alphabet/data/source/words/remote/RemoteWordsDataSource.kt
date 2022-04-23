package com.github.kiolk.alphabet.data.source.words.remote

import com.github.kiolk.alphabet.data.models.game.GameSettings
import com.github.kiolk.alphabet.data.models.topic.Topic
import com.github.kiolk.alphabet.data.models.topic.local.TopicWithPhoto
import com.github.kiolk.alphabet.data.models.topic.local.TotalReadWordsTopic
import com.github.kiolk.alphabet.data.models.topic.local.TotalWordsTopic
import com.github.kiolk.alphabet.data.models.word.Mistake
import com.github.kiolk.alphabet.data.models.word.Word
import com.github.kiolk.alphabet.data.models.words.Words
import com.github.kiolk.alphabet.data.source.words.WordsDataSource
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.BackpressureStrategy
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject
import timber.log.Timber
import javax.inject.Inject

class RemoteWordsDataSource
@Inject
constructor(private val service: WordsService, private val database: FirebaseDatabase) :
    WordsDataSource {

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
        val wordsRef = database.getReference(ROOT_DATABASE_CHILD_KEY)
        val subject: PublishSubject<List<Word>> = PublishSubject.create()

        wordsRef.get().addOnSuccessListener {
            val words: List<Word> = it.children.map { wordDataSnapshot ->
                wordDataSnapshot.toWord()
            }
            subject.onNext(words)
        }.addOnFailureListener {
            Timber.d(it)
            subject.onNext(emptyList())
        }

        return subject.toFlowable(BackpressureStrategy.BUFFER)
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

    private companion object {
        const val ROOT_DATABASE_CHILD_KEY = "words"
    }
}

fun DataSnapshot.toWord(): Word {
    return Word(
        this.child("value").value.toString(),
        this.child("syllables").value.toString(),
        this.child("image").value.toString(),
        this.child("tag").value.toString(),
        0,
        this.child("image_author").value.toString()
    )
}