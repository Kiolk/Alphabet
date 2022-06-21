package com.github.kiolk.common.domain.repository.image.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.github.kiolk.common.data.database.models.image.ImageDb
import io.reactivex.Completable

@Dao
interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertImage(list: List<ImageDb>): Completable
}