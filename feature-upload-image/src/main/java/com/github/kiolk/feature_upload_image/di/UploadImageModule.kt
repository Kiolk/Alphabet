package com.github.kiolk.feature_upload_image.di

import com.github.kiolk.feature_upload_image.domain.UploadImageUseCase
import com.github.kiolk.feature_upload_image.domain.UploadImageUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
internal interface UploadImageModule {

    @Binds
    fun bindUploadImageUseCaseImplToUploadImageUseCase(useCaseImpl: UploadImageUseCaseImpl): UploadImageUseCase
}