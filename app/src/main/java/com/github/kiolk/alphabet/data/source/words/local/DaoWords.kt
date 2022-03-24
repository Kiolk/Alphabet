package com.github.kiolk.alphabet.data.source.words.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.kiolk.alphabet.data.models.words.Words
import io.reactivex.Flowable

@Dao
interface DaoWords {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(words: Words)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<Words>)

    @Query("SELECT * FROM Words")
    fun getWords(): Flowable<List<Words>>
}