package com.github.kiolk.feature_upload_image.presentation.select

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.app.imagepickerlibrary.ImagePickerActivityClass
import com.app.imagepickerlibrary.ImagePickerBottomsheet
import com.bluelinelabs.conductor.ChangeHandlerFrameLayout
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.github.kiolk.feature_upload_image.R

class SelectImageActivity : FragmentActivity(), ImagePickerBottomsheet.ItemClickListener,
    ImagePickerActivityClass.OnResult {

    lateinit var controllerContainer: ChangeHandlerFrameLayout
    lateinit var router: Router
    lateinit var imagePicker: ImagePickerActivityClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_image)
        controllerContainer = findViewById(R.id.select_image_controller_container)

        router = Conductor.attachRouter(
            this,
            findViewById(R.id.select_image_controller_container),
            savedInstanceState
        )
        if (!router.hasRootController()) {
            router.setRoot(
                RouterTransaction.with(SelectImageController()).tag((SelectImageController.TAG))
            )
        }

        imagePicker =
            ImagePickerActivityClass(baseContext, this, activityResultRegistry, activity = this)
        imagePicker.cropOptions(true)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun returnString(item: Uri?) {
        (router.getControllerWithTag(SelectImageController.TAG) as SelectImageController).onImageSelected(
            item!!
        )
    }

    override fun onItemClick(item: String?) {
        when (item) {
            "Camera" -> imagePicker.takePhotoFromCamera()
            "Gallery" -> imagePicker.choosePhotoFromGallery()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        imagePicker.onActivityResult(requestCode, resultCode, data)
    }
}