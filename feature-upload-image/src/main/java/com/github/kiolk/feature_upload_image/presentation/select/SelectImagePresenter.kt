package com.github.kiolk.feature_upload_image.presentation.select

import android.net.Uri
import com.arellomobile.mvp.InjectViewState
import com.github.kiolk.common.presentation.base.BasePresenter
import com.github.kiolk.common.presentation.base.addToDisposable
import com.github.kiolk.feature_upload_image.domain.UploadImageUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@InjectViewState
class SelectImagePresenter @Inject constructor(
    private val uploadImageUseCase: UploadImageUseCase
) : BasePresenter<SelectImageView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.selectImageFromGallery()
    }

    fun uploadImage(uri: Uri) {
        uploadImageUseCase.execute(UploadImageUseCase.Params(uri))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSuccessUpload, ::onErrorUpload)
            .addToDisposable(this)
    }

    private fun onSuccessUpload(url: String) {
//        Toast.makeText(getContext(), "Success $url", Toast.LENGTH_LONG).show()
    }

    private fun onErrorUpload(exception: Throwable) {
//        Toast.makeText(getContext(), "Error $exception", Toast.LENGTH_LONG).show()
    }
}