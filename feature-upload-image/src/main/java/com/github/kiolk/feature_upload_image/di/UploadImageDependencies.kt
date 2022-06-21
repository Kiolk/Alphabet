package com.github.kiolk.feature_upload_image.di

import android.content.Context
import com.github.kiolk.common.data.holders.AppInfoComponentHolder
import com.github.kiolk.common.data.model.appInfo.AppInfo
import com.github.kiolk.common.domain.repository.word.WordsRepository
import com.github.kiolk.common_di.commonHolders.ContextComponentHolder
import com.github.kiolk.common.domain.repository.WordsRepositoryComponentHolder

internal interface UploadImageDependencies {

    fun context(): Context

    fun getAppInfo(): AppInfo

    fun getWordRepository(): WordsRepository

    object Impl : UploadImageDependencies {
        override fun context(): Context = ContextComponentHolder.get().getContext()

        override fun getAppInfo(): AppInfo = AppInfoComponentHolder.get().getAppInfo()

        override fun getWordRepository(): WordsRepository =
            WordsRepositoryComponentHolder.get().getRepository()
    }
}