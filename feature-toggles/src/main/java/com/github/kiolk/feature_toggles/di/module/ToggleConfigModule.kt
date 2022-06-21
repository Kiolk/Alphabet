package com.github.kiolk.feature_toggles.di.module

import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ToggleConfigModule {

    @Singleton
    @Provides
    fun provideFirebaseRemoteConfig(): FirebaseRemoteConfig {
        return Firebase.remoteConfig.apply {
            setConfigSettingsAsync(remoteConfigSettings {
                minimumFetchIntervalInSeconds = 3600
            })
        }
    }
}