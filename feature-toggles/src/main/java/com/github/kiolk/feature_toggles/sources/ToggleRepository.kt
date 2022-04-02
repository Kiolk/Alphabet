package com.github.kiolk.feature_toggles.sources

import com.github.kiolk.feature_toggles.base.Toggle

interface ToggleRepository {

    fun <T> getToggle(clazz: Class<T>): T where T : Toggle
}