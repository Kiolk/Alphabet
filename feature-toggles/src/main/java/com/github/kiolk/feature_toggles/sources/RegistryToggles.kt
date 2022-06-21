package com.github.kiolk.feature_toggles.sources

import com.github.kiolk.feature_toggles.base.FeatureToggle
import javax.inject.Inject
import kotlin.reflect.KClass

interface RegistryToggles {

    val toggles: MutableList<KClass<out FeatureToggle<Any>>>

    fun <T : FeatureToggle<Any>> addToggle(kClass: KClass<T>)
}

class RegistryTogglesImpl @Inject constructor() : RegistryToggles {

    override val toggles: MutableList<KClass<out FeatureToggle<Any>>> = mutableListOf(

    )

    override fun <T : FeatureToggle<Any>> addToggle(kClass: KClass<T>) {
        toggles.add(kClass)
    }
}