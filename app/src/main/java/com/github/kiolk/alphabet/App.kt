package com.github.kiolk.alphabet

import android.app.Application
import com.github.kiolk.alphabet.di.compomemts.ApplicationComponent
import com.github.kiolk.alphabet.di.compomemts.DaggerApplicationComponent
import com.github.kiolk.alphabet.di.modules.AppModule
import com.github.kiolk.alphabet.di.modules.DbModule
import com.github.kiolk.alphabet.di.modules.NetworkModule
import com.github.kiolk.feature_toggles.di.FeatureToggleComponentHolder

open class App : Application() {

    override fun onCreate() {
        super.onCreate()

        component = buildAppComponent()
    }

    private fun buildAppComponent(): ApplicationComponent {
        return DaggerApplicationComponent
            .factory()
            .create(
                FeatureToggleComponentHolder.get(),
                AppModule(this),
                DbModule(),
                NetworkModule()
            )
    }

    companion object {
        @JvmStatic
        lateinit var component: ApplicationComponent
    }
}