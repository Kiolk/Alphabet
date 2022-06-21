package com.github.kiolk.feature_upload_image.presentation.select

import com.github.kiolk.common.data.model.word.Word
import com.github.kiolk.common.presentation.base.BaseView

interface SelectImageView : BaseView {

    fun selectImageFromGallery()

    fun successUploadImage(word: Word)
}