package com.github.kiolk.feature_toggles.provider

import com.github.kiolk.feature_toggles.base.FeatureToggle
import com.github.kiolk.feature_toggles.sources.LocalToggleDataSource
import com.github.kiolk.feature_toggles.sources.RegistryToggles
import com.github.kiolk.feature_toggles.sources.RegistryTogglesImpl
import com.github.kiolk.feature_toggles.sources.ToggleDataSource
import com.github.kiolk.feature_toggles.sources.ToggleRepository
import com.github.kiolk.feature_toggles.sources.ToggleRepositoryImpl
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.reflect.KClass

class FeatureToggleProviderTest {

    @Test
    fun `Check correct provide for feature toggle with default value true`() {
        val testToggle = TestToggleWithDefault()
        val provider: FeatureToggleProvider = createProvider(TestToggleWithDefault::class)

        val actualToggle = provider.provide(TestToggleWithDefault::class.java)

        assertEquals(testToggle.isEnable(), actualToggle.isEnable())
        assertEquals(testToggle.name, actualToggle.name)
    }

    @Test
    fun `Check correct provide for feature toggle with value false`() {
        val testToggle = TestToggleWithDisable()
        val provider: FeatureToggleProvider = createProvider(TestToggleWithDisable::class)

        val actualToggle = provider.provide(TestToggleWithDisable::class.java)

        assertEquals(testToggle.isEnable(), actualToggle.isEnable())
        assertEquals(testToggle.name, actualToggle.name)
    }

    private fun createProvider(kClass: KClass<out FeatureToggle>): FeatureToggleProvider {
        val registry: RegistryToggles = RegistryTogglesImpl.apply { addToggle(kClass) }
        val dataSource: ToggleDataSource = LocalToggleDataSource(registry)
        val repository: ToggleRepository = ToggleRepositoryImpl(dataSource)

        return FeatureToggleProviderImpl(repository)
    }

    class TestToggleWithDefault : FeatureToggle() {

        override val name: String = "Test toggle"

        override val defaultValue: Boolean = true
    }

    class TestToggleWithDisable : FeatureToggle() {

        override val name: String = "TTestToggleWithDisable"

        override val enable: Boolean = false

        override val defaultValue: Boolean = true
    }
}