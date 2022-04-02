package com.github.kiolk.feature_toggles.provider

import com.github.kiolk.feature_toggles.base.FeatureToggle
import com.github.kiolk.feature_toggles.sources.ToggleRepository

class FeatureToggleProviderImpl(private val repository: ToggleRepository) : FeatureToggleProvider {

    override fun <T> provide(clazz: Class<T>): T where T : FeatureToggle {
        return repository.getToggle(clazz)
    }
}