package com.github.kiolk.feature_toggles.provider

import com.github.kiolk.feature_toggles.base.FeatureToggle
import com.github.kiolk.feature_toggles.sources.ToggleRepository
import javax.inject.Inject
import kotlin.reflect.KClass

class FeatureToggleProviderImpl @Inject constructor(private val repository: ToggleRepository) :
    FeatureToggleProvider {

    override fun <T> provide(clazz: KClass<*>): FeatureToggle<T> {
        return repository.getToggle(clazz)
    }
}