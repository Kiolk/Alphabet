package com.github.kiolk.alphabet

import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric

class ReleaseApp: App() {

    override fun onCreate() {
        super.onCreate()
        intitCrashlitic()
    }

    private fun intitCrashlitic() {
        Fabric.with(this, Crashlytics())
    }
}