package com.github.kiolk.feature_upload_image.presentation.select

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.github.kiolk.common.presentation.controller.BaseDaggerController
import com.github.kiolk.feature_upload_image.R
import com.github.kiolk.feature_upload_image.di.UploadImageComponentHolder

class SelectImageController : BaseDaggerController<SelectImageView, SelectImagePresenter>() {

    @InjectPresenter
    lateinit var presenter: SelectImagePresenter

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup): View =
        inflater.inflate(
            R.layout.controller_select_image, container, false
        )

    override fun preparePresenter(): SelectImagePresenter {
        return UploadImageComponentHolder.get().getSelectImagePresenter()
    }

    companion object {
        const val TAG = "SelectImageController"
    }
}