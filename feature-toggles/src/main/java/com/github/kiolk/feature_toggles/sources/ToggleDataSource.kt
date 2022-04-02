package com.github.kiolk.feature_toggles.sources

import com.github.kiolk.feature_toggles.base.Toggle

interface ToggleDataSource {

    fun fetchToggles()

    fun <T : Toggle> getToggle(clazz: Class<T>): T
}