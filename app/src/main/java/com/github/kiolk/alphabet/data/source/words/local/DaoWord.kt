package com.github.kiolk.alphabet.data.source.words.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.github.kiolk.alphabet.data.models.topic.local.TopicWithPhoto
import com.github.kiolk.alphabet.data.models.topic.local.TotalReadWordsTopic
import com.github.kiolk.alphabet.data.models.topic.local.TotalWordsTopic
import com.github.kiolk.alphabet.data.models.word.Word
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface DaoWord {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(word: Word)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<Word>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateWord(word: Word)

    @Query("SELECT * FROM Word")
    fun getAllWords(): Flowable<List<Word>>

    @Query("SELECT * FROM Word WHERE word_mean LIKE :query AND word_mean LIKE :second AND word_mean LIKE :s")
    fun getSelectedWord(query: String, second: String, s: String = "%"): Flowable<List<Word>>

    @Query("SELECT * FROM Word WHERE word_mean LIKE :query AND word_mean LIKE :second AND word_mean LIKE :s")
    fun getSelectedBySettingsWords(query: String, second: String, s: String = "%"): Flowable<List<Word>>

    @Query("SELECT * FROM Word WHERE tags LIKE :query")
    fun getWordsByTag(query: String): Flowable<List<Word>>

    @Query("SELECT tags FROM Word GROUP BY tags")
    fun getAvailableTopic(): Single<List<String>>

    @Query("SELECT COUNT(*) AS count, tags FROM Word GROUP BY tags")
    fun countTotalWordsTopic(): Flowable<List<TotalWordsTopic>>

    @Query("SELECT COUNT(*) AS count, tags FROM Word WHERE read > 0 GROUP BY tags")
    fun countReadTotalWordsTopic(): Flowable<List<TotalReadWordsTopic>>

    @Query("SELECT word_image, tags FROM Word WHERE tags LIKE word_mean")
    fun getTopicsWithImage(): Flowable<List<TopicWithPhoto>>

    @Query("SELECT * FROM Word WHERE :topic LIKE tags")
    fun getTopicWords(topic: String): Flowable<List<Word>>
}