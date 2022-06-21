package com.github.kiolk.feature_upload_image.domain

import com.github.kiolk.common.data.model.image.Image
import com.github.kiolk.common.data.model.word.Word
import com.github.kiolk.common.domain.base.UseCase
import com.github.kiolk.common.domain.repository.word.WordsRepository
import io.reactivex.Single
import javax.inject.Inject

interface AddedImageToWordUseCase : UseCase<Single<Word>, AddedImageToWordUseCase.Param> {

    class Param(val word: Word, val image: Image)
}

internal class AddedImageToWordUseCaseImpl @Inject constructor(
    private val repository: WordsRepository
) : AddedImageToWordUseCase {

    override fun execute(params: AddedImageToWordUseCase.Param): Single<Word> {
        return repository.addImageToWord(params.word, params.image)
            .andThen(repository.getWordById(params.word.id))
    }
}

