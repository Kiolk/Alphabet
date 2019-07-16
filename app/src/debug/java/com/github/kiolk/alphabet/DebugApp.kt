package com.github.kiolk.alphabet

import com.facebook.stetho.Stetho
import timber.log.Timber

class DebugApp: App() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)

        Timber.asTree()
    }
}