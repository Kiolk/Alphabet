package com.github.kiolk.alphabet.data.source.words.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.github.kiolk.alphabet.data.models.word.Word
import io.reactivex.Flowable

@Dao
interface DaoWord {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(word : Word)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list : List<Word>)

    @Query("SELECT * FROM Word")
    fun getAllWords() : Flowable<List<Word>>

    @Query("SELECT * FROM Word WHERE word_mean LIKE :query AND word_mean LIKE :second AND word_mean LIKE :s")
    fun getSelectedWord(query: String, second : String, s : String = "%"): Flowable<List<Word>>

    @Query("SELECT * FROM Word WHERE tags LIKE :query")
    fun getWordsByTag(query : String) : Flowable<List<Word>>
}