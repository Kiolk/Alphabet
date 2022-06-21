package com.github.kiolk.common.domain.repository.image

import com.github.kiolk.common.data.database.models.image.ImageDb
import io.reactivex.Completable

interface ImageDataSource {

    fun setImages(images: List<ImageDb>): Completable
}