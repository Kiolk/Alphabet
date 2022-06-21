package com.github.kiolk.feature_toggles.base

abstract class FeatureToggle<T> : Toggle<T> {

    abstract val defaultValue: T

    override var value: T = defaultValue
        get() {
            return field ?: defaultValue
        }

    abstract fun getType(): ValueToggleType
}