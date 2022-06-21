package com.github.kiolk.common.domain.repository.image

import com.github.kiolk.common.data.model.image.Image
import io.reactivex.Completable

interface ImageRepository {

    fun setImages(images: List<Image>): Completable
}