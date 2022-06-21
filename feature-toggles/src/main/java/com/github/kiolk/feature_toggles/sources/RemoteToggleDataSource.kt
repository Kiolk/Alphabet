package com.github.kiolk.feature_toggles.sources

import com.github.kiolk.feature_toggles.base.FeatureToggle
import com.github.kiolk.feature_toggles.base.ValueToggleType
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import javax.inject.Inject
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

class RemoteToggleDataSource @Inject constructor(private val firebaseConfig: FirebaseRemoteConfig) :
    ToggleDataSource {

    override fun fetchToggles() {
        firebaseConfig.fetchAndActivate()
    }

    override fun <T> getToggle(clazz: KClass<*>): FeatureToggle<T>? {
        val toggle = (clazz.createInstance() as? FeatureToggle<T>) ?: return null
        val value = when (toggle.getType()) {
            ValueToggleType.BOOLEAN -> {
                firebaseConfig.getBoolean(toggle.name)
            }
            else -> null
        }
        toggle.value = (value as? T) ?: return null
        return toggle
    }
}