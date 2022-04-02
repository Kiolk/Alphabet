package com.github.kiolk.feature_toggles.sources

import com.github.kiolk.feature_toggles.base.Toggle
import javax.inject.Inject

class ToggleRepositoryImpl @Inject constructor(private val datasource: ToggleDataSource) :
    ToggleRepository {

    init {
        datasource.fetchToggles()
    }

    override fun <T : Toggle> getToggle(clazz: Class<T>): T {
        return datasource.getToggle(clazz)
    }
}