package com.github.kiolk.feature_toggles.di.module

import com.github.kiolk.feature_toggles.provider.FeatureToggleProvider
import com.github.kiolk.feature_toggles.provider.FeatureToggleProviderImpl
import com.github.kiolk.feature_toggles.sources.LocalToggleDataSource
import com.github.kiolk.feature_toggles.sources.RegistryToggles
import com.github.kiolk.feature_toggles.sources.RegistryTogglesImpl
import com.github.kiolk.feature_toggles.sources.ToggleDataSource
import com.github.kiolk.feature_toggles.sources.ToggleRepository
import com.github.kiolk.feature_toggles.sources.ToggleRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
internal interface FeatureToggleModule {

    @Binds
    fun bindFeatureToggleProvider(impl: FeatureToggleProviderImpl): FeatureToggleProvider

    @Binds
    fun bindFeatureToggleRepository(impl: ToggleRepositoryImpl): ToggleRepository

    @Binds
    fun bindDataSource(impl: LocalToggleDataSource): ToggleDataSource

    @Binds
    fun bindToggleRegistry(impl: RegistryTogglesImpl): RegistryToggles
}