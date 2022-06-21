package com.github.kiolk.feature_upload_image.domain

import com.github.kiolk.common.data.model.word.Word
import com.github.kiolk.common.domain.base.UseCase
import com.github.kiolk.common.domain.repository.word.WordsRepository
import io.reactivex.Single

interface GetWordWithoutImagesUseCase :
    UseCase<Single<List<Word>>, GetWordWithoutImagesUseCase.Params> {

    class Params
}

internal class GetWordWithoutImagesUseCaseImp(private val repository: WordsRepository) :
    GetWordWithoutImagesUseCase {

    override fun execute(params: GetWordWithoutImagesUseCase.Params): Single<List<Word>> {
        return repository.getAllDbWords().singleOrError().map { it.filter { it.images.isEmpty() } }
    }
}

