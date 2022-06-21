package com.github.kiolk.common.domain.repository.word.mapper

import com.github.kiolk.common.data.database.models.word.WordDb
import com.github.kiolk.common.data.database.models.word.WordWithImagesDb
import com.github.kiolk.common.data.model.word.Word
import com.github.kiolk.common.domain.repository.image.mapper.toDomain

fun WordDb.toDomain(): Word {
    return Word(
        value = value,
        syllables = syllables,
        image = image,
        tags = tags,
        read = read,
        author = author,
        id = id,
    )
}

fun Word.toDb(): WordDb {
    return WordDb(
        value = value,
        syllables = syllables,
        image = image,
        tags = tags,
        read = read,
        author = author,
        id = id,
    )
}

fun WordWithImagesDb.toDomain(): Word {
    return Word(
        value = value,
        syllables = syllables,
        image = image,
        tags = tags,
        read = read,
        author = author,
        id = id,
        images = images.map { it.toDomain() }
    )
}