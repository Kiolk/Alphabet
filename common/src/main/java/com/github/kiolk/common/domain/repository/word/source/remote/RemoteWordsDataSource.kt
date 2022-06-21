package com.github.kiolk.common.domain.repository.word.source.remote

import com.github.kiolk.common.data.model.appInfo.AppInfo
import com.github.kiolk.common.data.model.image.Image
import com.github.kiolk.common.data.model.word.GameSettings
import com.github.kiolk.common.data.model.word.Mistake
import com.github.kiolk.common.data.model.word.Topic
import com.github.kiolk.common.data.model.word.TopicWithPhoto
import com.github.kiolk.common.data.model.word.TotalReadWordsTopic
import com.github.kiolk.common.data.model.word.TotalWordsTopic
import com.github.kiolk.common.data.model.word.Word
import com.github.kiolk.common.data.model.word.Words
import com.github.kiolk.common.domain.repository.word.WordsDataSource
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import io.reactivex.BackpressureStrategy
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject
import java.lang.Exception
import javax.inject.Inject

class RemoteWordsDataSource
@Inject
constructor(
    private val service: WordsService,
    private val database: FirebaseDatabase,
    private val appInfo: AppInfo
) : WordsDataSource {

    private val wordsRef = database.getReference(appInfo.databaseRootPath)

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
        val wordsRef = database.getReference(appInfo.databaseRootPath)
        val subject: PublishSubject<List<Word>> = PublishSubject.create()

        wordsRef.get().addOnSuccessListener {
            val words: List<Word> = it.children.map { wordDataSnapshot ->
                wordDataSnapshot.toWord()
            }
            subject.onNext(words)
        }.addOnFailureListener {
//            Timber.d(it)
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

    override fun addImageToWord(word: Word, image: Image): Completable {
        return Completable.create { emitter ->
            val wordsRef = database.getReference(appInfo.databaseRootPath)
            wordsRef.child(word.id).child("images").child(word.images.size.toString())
                .setValue(image.copy(wordId = word.id))
                .addOnFailureListener { emitter.onError(it) }
                .addOnSuccessListener { emitter.onComplete() }
        }
    }

    override fun getWordById(wordId: String): Single<Word> {
        return Single.create { emitter ->
            wordsRef.child(wordId).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    emitter.onSuccess(p0.toWord())
                }

                override fun onCancelled(p0: DatabaseError) {
                    emitter.onError(p0.toException())
                }

            })
        }
    }
}

fun DataSnapshot.toWord(): Word {
    val images: List<Image> = try {
        this.child("images").children.map { (it.getValue(Image::class.java) ?: Image()) }
    } catch (e: Exception) {
        emptyList()
    }
    return Word(
        this.child("value").value.toString(),
        this.child("syllables").value.toString(),
        this.child("image").value.toString(),
        this.child("tag").value.toString(),
        0,
        this.child("image_author").value.toString(),
        id = this.key ?: "",
        images = images
    )
}
