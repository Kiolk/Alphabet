package com.github.kiolk.alphabet

import com.crashlytics.android.Crashlytics

class ReleaseApp: App() {

    override fun onCreate() {
        super.onCreate()
        intitCrashlitic()
    }

    private fun intitCrashlitic() {
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)
    }
}