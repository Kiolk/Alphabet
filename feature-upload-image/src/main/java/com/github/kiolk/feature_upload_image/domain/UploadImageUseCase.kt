package com.github.kiolk.feature_upload_image.domain

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.core.net.toUri
import com.github.kiolk.common.data.model.appInfo.AppInfo
import com.github.kiolk.common.domain.base.UseCase
import com.github.kiolk.feature_upload_image.BuildConfig
import com.google.firebase.storage.FirebaseStorage
import io.reactivex.Observable
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import javax.inject.Inject

interface UploadImageUseCase : UseCase<Observable<String>, UploadImageUseCase.Params> {

    class Params(val imageUri: Uri)
}

class UploadImageUseCaseImpl @Inject constructor(
    private val context: Context,
    private val appInfo: AppInfo
) : UploadImageUseCase {

    override fun execute(params: UploadImageUseCase.Params): Observable<String> {
        val storageFirebase = FirebaseStorage.getInstance()
        val storageRef = storageFirebase.reference
        val config = BuildConfig.LIBRARY_PACKAGE_NAME
        val mountainsImageRef =
            storageRef.child(appInfo.imageStorageRootPath + "images/${params.imageUri.lastPathSegment ?: ""}")
        val resizedUri = resizeImage(context, params.imageUri)

        return Observable.create { emiter ->
            val task = mountainsImageRef.putFile(resizedUri)
            task.continueWithTask { taskContinu ->
                if (!taskContinu.isSuccessful) emiter.onError(taskContinu.exception!!)
                emiter.onNext(task.result.toString())
                mountainsImageRef.downloadUrl
            }
        }
    }

    private fun resizeImage(context: Context, uri: Uri): Uri {
        val inputStream: InputStream = context.contentResolver.openInputStream(uri) ?: return uri
        val fileName = getFileName(context, uri)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        val height = bitmap.height
        val width = bitmap.width

        val widthDest = 400f

        if (width > widthDest) {
            val heightDest = height / (width / widthDest)
            val scaledBitmap =
                Bitmap.createScaledBitmap(bitmap, widthDest.toInt(), heightDest.toInt(), false)
            val byteArrayOutputStream: ByteArrayOutputStream = ByteArrayOutputStream()
            scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 70, byteArrayOutputStream)
            val file = File(context.cacheDir.absolutePath + File.pathSeparator + "test.jpg")
            file.createNewFile()
            val fileOutputStream: FileOutputStream = FileOutputStream(file)
            fileOutputStream.write(byteArrayOutputStream.toByteArray())
            fileOutputStream.close()
            return file.toUri()
        }

        return uri

    }

    private fun getFileName(context: Context, uri: Uri): String {
        return "test"
    }
}