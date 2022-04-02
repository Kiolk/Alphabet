package com.github.kiolk.feature_toggles.sources

import com.github.kiolk.feature_toggles.base.Toggle
import javax.inject.Inject
import kotlin.reflect.full.createInstance

class LocalToggleDataSource @Inject constructor(private val registryToggles: RegistryToggles) :
    ToggleDataSource {

    private val cache: MutableMap<String, Toggle> = mutableMapOf()

    override fun fetchToggles() {
        registryToggles.toggles.forEach {
            cache[it.java.simpleName] = it.createInstance()
        }
    }

    override fun <T : Toggle> getToggle(clazz: Class<T>): T {
        return cache[clazz.simpleName] as T
    }
}