package com.github.kiolk.feature_upload_image.di

import android.content.Context
import com.github.kiolk.common.data.holders.AppInfoComponentHolder
import com.github.kiolk.common.data.model.appInfo.AppInfo
import com.github.kiolk.common_di.commonHolders.ContextComponentHolder

internal interface UploadImageDependencies {

    fun context(): Context

    fun getAppInfo(): AppInfo

    object Impl : UploadImageDependencies {
        override fun context(): Context = ContextComponentHolder.get().getContext()

        override fun getAppInfo(): AppInfo = AppInfoComponentHolder.get().getAppInfo()
    }
}