package com.github.kiolk.feature_upload_image.presentation.select

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.app.imagepickerlibrary.ImagePickerBottomsheet
import com.arellomobile.mvp.presenter.InjectPresenter
import com.github.kiolk.common.presentation.controller.BaseDaggerController
import com.github.kiolk.common.utils.fragmentManager
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
        val bottomSheet = ImagePickerBottomsheet()
        bottomSheet.show(this.fragmentManager(), IMAGE_PICKER_TAG)
    }

    fun onImageSelected(imageUri: Uri) {
        view?.findViewById<TextView>(R.id.ImageUri)?.text = imageUri.toString()
    }

    companion object {
        const val TAG = "SelectImageController"
        private const val IMAGE_PICKER_TAG = "ImagePickerBottomSheet"
    }
}