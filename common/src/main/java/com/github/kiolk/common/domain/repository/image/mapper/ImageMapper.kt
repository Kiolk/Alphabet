package com.github.kiolk.common.domain.repository.image.mapper

import com.github.kiolk.common.data.database.models.image.ImageDb
import com.github.kiolk.common.data.model.image.Image

fun Image.toDb(): ImageDb {
    return ImageDb(
        url = url,
        author = author,
        isReviewed = isReviewed,
        wordId = wordId
    )
}


fun ImageDb.toDomain(): Image {
    return Image(
        url = url,
        author = author,
        isReviewed = isReviewed,
        wordId = wordId
    )
}