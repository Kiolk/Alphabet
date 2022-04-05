package com.github.kiolk.feature_upload_image.di

import com.github.kiolk.common_di.base.DIComponent
import com.github.kiolk.common_di.holder.FeatureComponentHolder
import com.github.kiolk.feature_upload_image.presentation.select.SelectImagePresenter
import dagger.Component
import javax.inject.Singleton

interface UploadImageComponent : DIComponent {

    fun getSelectImagePresenter(): SelectImagePresenter
}

@Singleton
@Component(
    dependencies = [UploadImageDependencies::class],
    modules = [UploadImageModule::class]
)
internal interface UploadImageComponentInternal : UploadImageComponent {

    @Component.Factory
    interface Factory {
        fun create(uploadImageDependencies: UploadImageDependencies): UploadImageComponentInternal
    }
}

internal object UploadImageComponentHolder : FeatureComponentHolder<UploadImageComponent>() {

    override fun build(): UploadImageComponent {
        return DaggerUploadImageComponentInternal
            .factory()
            .create(UploadImageDependencies.Impl)
    }
}
