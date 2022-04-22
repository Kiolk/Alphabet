package com.github.kiolk.feature_toggles.sources

import com.github.kiolk.feature_toggles.base.FeatureToggle
import com.github.kiolk.feature_toggles.base.Toggle
import javax.inject.Inject
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

class LocalToggleDataSource @Inject constructor(private val registryToggles: RegistryToggles) :
    ToggleDataSource {

    private val cache: MutableMap<String, Toggle<Any>> = mutableMapOf()

    override fun fetchToggles() {
        registryToggles.toggles.forEach {
            cache[it.java.simpleName] = it.createInstance()
        }
    }

    override fun <T> getToggle(clazz: KClass<*>): FeatureToggle<T>? {
        return cache[clazz.simpleName] as? FeatureToggle<T>?
    }
}