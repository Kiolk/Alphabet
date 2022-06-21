package com.github.kiolk.common.domain.repository.word.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.github.kiolk.common.data.database.models.image.ImageDb
import com.github.kiolk.common.data.database.models.word.WordDb
import com.github.kiolk.common.data.database.models.word.WordWithImagesDb
import com.github.kiolk.common.data.model.word.TopicWithPhoto
import com.github.kiolk.common.data.model.word.TotalReadWordsTopic
import com.github.kiolk.common.data.model.word.TotalWordsTopic
import com.github.kiolk.common.data.model.word.Word
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface DaoWord {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(word: WordDb)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<WordDb>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateWord(word: WordDb)

    @Query("SELECT * FROM Word")
    fun getAllWords(): Flowable<List<WordDb>>

    @Query("SELECT * FROM Word WHERE word_mean LIKE :query AND word_mean LIKE :second AND word_mean LIKE :s")
    fun getSelectedWord(query: String, second: String, s: String = "%"): Flowable<List<WordDb>>

    @Query("SELECT * FROM Word WHERE word_mean LIKE :query AND word_mean LIKE :second AND word_mean LIKE :s")
    fun getSelectedBySettingsWords(
        query: String,
        second: String,
        s: String = "%"
    ): Flowable<List<WordDb>>

    @Query("SELECT * FROM Word WHERE tags LIKE :query")
    fun getWordsByTag(query: String): Flowable<List<WordDb>>

    @Query("SELECT tags FROM Word GROUP BY tags")
    fun getAvailableTopic(): Single<List<String>>

    @Query("SELECT COUNT(*) AS count, tags FROM Word GROUP BY tags")
    fun countTotalWordsTopic(): Flowable<List<TotalWordsTopic>>

    @Query("SELECT COUNT(*) AS count, tags FROM Word WHERE read > 0 GROUP BY tags")
    fun countReadTotalWordsTopic(): Flowable<List<TotalReadWordsTopic>>

    @Query("SELECT word_image, tags FROM Word WHERE tags LIKE word_mean")
    fun getTopicsWithImage(): Flowable<List<TopicWithPhoto>>

    @Query("SELECT * FROM Word WHERE :topic LIKE tags")
    fun getTopicWords(topic: String): Flowable<List<WordDb>>

    @Transaction
    @Query("SELECT word_mean, word_by_syllables, word_image, tags, read, author, id FROM Word")
    fun getWordsWithImage(): Flowable<List<WordWithImagesDb>>
}