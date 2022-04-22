package com.github.kiolk.feature_toggles.provider

import com.github.kiolk.feature_toggles.base.FeatureToggle
import kotlin.reflect.KClass

interface FeatureToggleProvider {

    fun <T> provide(clazz: KClass<*>): FeatureToggle<T>
}