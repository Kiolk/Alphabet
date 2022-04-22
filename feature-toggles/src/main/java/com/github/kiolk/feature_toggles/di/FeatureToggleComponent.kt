package com.github.kiolk.feature_toggles.di

import com.github.kiolk.common_di.base.DIComponent
import com.github.kiolk.common_di.holder.FeatureComponentHolder
import com.github.kiolk.feature_toggles.di.module.FeatureToggleModule
import com.github.kiolk.feature_toggles.di.module.ToggleConfigModule
import com.github.kiolk.feature_toggles.provider.FeatureToggleProvider
import dagger.Component
import javax.inject.Singleton

interface FeatureToggleComponent : DIComponent {

    fun provideFeatureToggleProvider(): FeatureToggleProvider
}

@Singleton
@Component(
    dependencies = [FeatureToggleComponentDependencies::class],
    modules = [FeatureToggleModule::class, ToggleConfigModule::class]
)
interface FeatureToggleComponentInternal : FeatureToggleComponent {

    @Component.Factory
    interface Factory {
        fun create(featureToggleComponentDependencies: FeatureToggleComponentDependencies): FeatureToggleComponentInternal
    }
}

interface FeatureToggleComponentDependencies {

    object Impl : FeatureToggleComponentDependencies
}

object FeatureToggleComponentHolder : FeatureComponentHolder<FeatureToggleComponent>() {

    override fun build(): FeatureToggleComponent {
        return DaggerFeatureToggleComponentInternal.factory().create(
            FeatureToggleComponentDependencies.Impl
        )
    }
}