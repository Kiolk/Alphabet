package com.github.kiolk.feature_toggles.provider

class FeatureToggleProviderTest {

//    @Test
//    fun `Check correct provide for feature toggle with default value true`() {
//        val testToggle = TestToggleWithDefault()
//        val provider: FeatureToggleProvider = createProvider(TestToggleWithDefault::class)
//
////        val actualToggle = provider.provide(TestToggleWithDefault::class)
////
////        assertEquals(testToggle.isEnable(), actualToggle.isEnable())
////        assertEquals(testToggle.name, actualToggle.name)
//    }
//
//    @Test
//    fun `Check correct provide for feature toggle with value false`() {
//        val testToggle = TestToggleWithDisable()
//        val provider: FeatureToggleProvider = createProvider(TestToggleWithDisable::class)
//
////        val actualToggle = provider.provide(TestToggleWithDisable::class)
////
////        assertEquals(testToggle.isEnable(), actualToggle.isEnable())
////        assertEquals(testToggle.name, actualToggle.name)
//    }
//
//    private fun createProvider(kClass: KClass<out FeatureToggle>): FeatureToggleProvider {
//        val registry: RegistryToggles = RegistryTogglesImpl().apply { addToggle(kClass) }
//        val dataSource: ToggleDataSource = LocalToggleDataSource(registry)
//        val remoteDataSource: ToggleDataSource = mock()
//        val repository: ToggleRepository = ToggleRepositoryImpl(dataSource, remoteDataSource)
//
//        return FeatureToggleProviderImpl(repository)
//    }
//
//    class TestToggleWithDefault : FeatureToggle() {
//
//        override val name: String = "Test toggle"
//
//        override val defaultValue: Boolean = true
//    }
//
//    class TestToggleWithDisable : FeatureToggle() {
//
//        override val name: String = "TTestToggleWithDisable"
//
//        override var value: Boolean? = false
//
//        override val defaultValue: Boolean = true
//    }
}