package com.github.kiolk.feature_toggles.base

abstract class FeatureToggle : Toggle {

    abstract val name: String

    protected open val enable: Boolean? = null

    protected open val defaultValue: Boolean = false

    override fun isEnable(): Boolean {
        return enable ?: defaultValue
    }
}