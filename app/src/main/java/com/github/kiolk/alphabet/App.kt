package com.github.kiolk.alphabet

import android.app.Application
import com.github.kiolk.alphabet.di.compomemts.ApplicationComponent
import com.github.kiolk.alphabet.di.compomemts.DaggerApplicationComponent
import com.github.kiolk.alphabet.di.modules.AppModule
import com.github.kiolk.alphabet.di.modules.DbModule
import com.github.kiolk.alphabet.di.modules.NetworkModule

open class App : Application() {

    override fun onCreate() {
        super.onCreate()

        component = buildAppComponent()
    }

    private fun buildAppComponent(): ApplicationComponent {
        return DaggerApplicationComponent.builder()
                .appModule(AppModule(this))
                .dbModule (DbModule())
                .networkModule(NetworkModule())
                .build()
    }

    companion object {
        @JvmStatic
        lateinit var component: ApplicationComponent
    }
}