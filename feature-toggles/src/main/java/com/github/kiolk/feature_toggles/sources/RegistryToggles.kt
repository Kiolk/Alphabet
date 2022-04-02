package com.github.kiolk.feature_toggles.sources

import com.github.kiolk.feature_toggles.base.FeatureToggle
import com.github.kiolk.feature_toggles.toggles.UploadImageFeatureToggle
import javax.inject.Inject
import kotlin.reflect.KClass

interface RegistryToggles {

    val toggles: MutableList<KClass<out FeatureToggle>>

    fun <T : FeatureToggle> addToggle(kClass: KClass<T>)
}

class RegistryTogglesImpl @Inject constructor() : RegistryToggles {

    override val toggles: MutableList<KClass<out FeatureToggle>> = mutableListOf(
        UploadImageFeatureToggle::class
    )

    override fun <T : FeatureToggle> addToggle(kClass: KClass<T>) {
        toggles.add(kClass)
    }
}