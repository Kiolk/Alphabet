package com.github.kiolk.feature_toggles.sources

import com.github.kiolk.common_di.qualifiers.LocalDataSource
import com.github.kiolk.common_di.qualifiers.RemoteDataSource
import com.github.kiolk.feature_toggles.base.FeatureToggle
import javax.inject.Inject
import kotlin.reflect.KClass

class ToggleRepositoryImpl @Inject constructor(
    @LocalDataSource private val local: ToggleDataSource,
    @RemoteDataSource private val remote: ToggleDataSource
) : ToggleRepository {

    init {
        local.fetchToggles()
        remote.fetchToggles()
    }

    override fun <T> getToggle(clazz: KClass<*>): FeatureToggle<T> {
        return remote.getToggle(clazz) ?: local.getToggle(clazz)
        ?: throw IllegalStateException("Feature toggle do not define")
    }
}