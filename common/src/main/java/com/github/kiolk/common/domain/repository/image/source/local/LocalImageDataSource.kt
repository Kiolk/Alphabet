package com.github.kiolk.common.domain.repository.image.source.local

import com.github.kiolk.common.data.database.models.image.ImageDb
import com.github.kiolk.common.domain.repository.image.ImageDataSource
import io.reactivex.Completable
import javax.inject.Inject

class LocalImageDataSource @Inject constructor(private val dao: ImageDao) : ImageDataSource {

    override fun setImages(images: List<ImageDb>): Completable {
        return dao.insertImage(images)
    }
}