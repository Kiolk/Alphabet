package com.github.kiolk.alphabet

import com.facebook.stetho.Stetho

class DebugApp: App() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}