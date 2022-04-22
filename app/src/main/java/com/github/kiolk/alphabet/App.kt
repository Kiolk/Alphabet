package com.github.kiolk.alphabet

import android.app.Application
import android.content.Context
import com.arellomobile.mvp.RegisterMoxyReflectorPackages
import com.github.kiolk.alphabet.di.compomemts.ApplicationComponent
import com.github.kiolk.alphabet.di.compomemts.DaggerApplicationComponent
import com.github.kiolk.alphabet.di.modules.AppModule
import com.github.kiolk.alphabet.di.modules.DbModule
import com.github.kiolk.alphabet.di.modules.NetworkModule
import com.github.kiolk.common.data.holders.AppInfoComponent
import com.github.kiolk.common.data.holders.AppInfoComponentHolder
import com.github.kiolk.common.data.model.appInfo.AppInfo
import com.github.kiolk.common_di.commonHolders.ContextComponent
import com.github.kiolk.common_di.commonHolders.ContextComponentHolder
import com.github.kiolk.feature_toggles.di.FeatureToggleComponentHolder
import com.github.kiolk.feature_upload_image.di.UploadImageComponentHolder

@RegisterMoxyReflectorPackages("com.github.kiolk.feature_upload_image")
open class App : Application() {

    override fun onCreate() {
        super.onCreate()

        component = buildAppComponent()
        initHolders()
    }

    private fun buildAppComponent(): ApplicationComponent {
        return DaggerApplicationComponent
            .factory()
            .create(
                UploadImageComponentHolder.get(),
                FeatureToggleComponentHolder.get(),
                AppModule(this),
                DbModule(),
                NetworkModule()
            )
    }

    private fun initHolders() {
        ContextComponentHolder.set {
            object : ContextComponent {
                override fun getContext(): Context = applicationContext
            }
        }
        AppInfoComponentHolder.set {
            object : AppInfoComponent {
                override fun getAppInfo(): AppInfo = AppInfo(
                    isDebug = BuildConfig.DEBUG,
                    imageStorageRootPath = BuildConfig.ImageStoreRootPath

                )
            }
        }
    }

    companion object {
        @JvmStatic
        lateinit var component: ApplicationComponent
    }
}