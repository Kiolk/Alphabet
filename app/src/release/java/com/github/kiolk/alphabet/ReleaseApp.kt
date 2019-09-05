package com.github.kiolk.alphabet

import android.util.Log
import com.crashlytics.android.Crashlytics
import com.facebook.stetho.Stetho
import io.fabric.sdk.android.Fabric
import timber.log.Timber

class ReleaseApp: App() {

    override fun onCreate() {
        super.onCreate()
        intitCrashlitic()
    }

    private fun intitCrashlitic() {
        Log.d("MyLogs", "Call init crashlitic")
        Fabric.with(this, Crashlytics())
    }
}