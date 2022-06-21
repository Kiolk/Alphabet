package com.github.kiolk.common.domain.repository.image

import com.github.kiolk.common.data.model.image.Image
import com.github.kiolk.common.domain.repository.image.mapper.toDb
import com.github.kiolk.common_di.qualifiers.LocalDataSource
import io.reactivex.Completable
import javax.inject.Inject

class RealImageRepository @Inject constructor(@LocalDataSource private val local: ImageDataSource) :
    ImageRepository {

    override fun setImages(images: List<Image>): Completable {
        return local.setImages(images.map { it.toDb() })
    }
}