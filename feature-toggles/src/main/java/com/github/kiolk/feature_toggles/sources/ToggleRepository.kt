package com.github.kiolk.feature_toggles.sources

import com.github.kiolk.feature_toggles.base.FeatureToggle
import kotlin.reflect.KClass

interface ToggleRepository {

    fun <T> getToggle(clazz: KClass<*>): FeatureToggle<T>
}