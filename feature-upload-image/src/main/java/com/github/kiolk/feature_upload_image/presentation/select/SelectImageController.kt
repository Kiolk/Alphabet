package com.github.kiolk.feature_upload_image.presentation.select

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.github.kiolk.common.presentation.controller.BaseDaggerController
import com.github.kiolk.feature_upload_image.R
import com.github.kiolk.feature_upload_image.di.UploadImageComponentHolder

class SelectImageController : BaseDaggerController<SelectImageView, SelectImagePresenter>(),
    SelectImageView {

    @InjectPresenter
    lateinit var presenter: SelectImagePresenter

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup): View =
        inflater.inflate(
            R.layout.controller_select_image, container, false
        )

    override fun preparePresenter(): SelectImagePresenter {
        return UploadImageComponentHolder.get().getSelectImagePresenter()
    }

    override fun selectImageFromGallery() {
        val intent: Intent = Intent()
        intent.type = "IMAGE_TYPE"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select image"), SELECT_PICTURE_RESULT)
    }

    companion object {
        const val TAG = "SelectImageController"
        private const val IMAGE_TYPE = "image/*"
        const val SELECT_PICTURE_RESULT = 1
    }
}