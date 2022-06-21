package com.github.kiolk.feature_upload_image.presentation.select

import android.net.Uri
import android.widget.Toast
import com.arellomobile.mvp.InjectViewState
import com.github.kiolk.common.data.model.image.Image
import com.github.kiolk.common.data.model.word.Word
import com.github.kiolk.common.presentation.base.BasePresenter
import com.github.kiolk.common.presentation.base.addToDisposable
import com.github.kiolk.feature_upload_image.domain.AddedImageToWordUseCase
import com.github.kiolk.feature_upload_image.domain.UploadImageUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.security.AccessController.getContext
import javax.inject.Inject

@InjectViewState
class SelectImagePresenter @Inject constructor(
    private val uploadImageUseCase: UploadImageUseCase,
    private val addImageToWordUseCase: AddedImageToWordUseCase,
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
        addImageToWordUseCase.execute(
            AddedImageToWordUseCase.Param(
                Word("", "", "", "", id = "2"),
                Image(url, "", wordId = "")
            )
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onUpdateWordImageSuccess, ::onErrorUpload)
            .addToDisposable(this)
    }

    private fun onUpdateWordImageSuccess(word: Word) {
        viewState.successUploadImage(word)
    }

    private fun onErrorUpload(exception: Throwable) {
//        Toast.makeText(getContext(), "Error $exception", Toast.LENGTH_LONG).show()
    }
}