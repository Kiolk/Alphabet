package com.github.kiolk.alphabet

import com.google.firebase.crashlytics.FirebaseCrashlytics


class ReleaseApp : App() {

    override fun onCreate() {
        super.onCreate()
        intitCrashlitic()
    }

    private fun intitCrashlitic() {
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)
    }
}