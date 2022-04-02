package com.github.kiolk.feature_toggles.provider

import com.github.kiolk.feature_toggles.base.FeatureToggle

interface FeatureToggleProvider {

    fun <T> provide(clazz: Class<T>): T where T : FeatureToggle
}