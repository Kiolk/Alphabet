package com.github.kiolk.alphabet.data.source.words.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
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