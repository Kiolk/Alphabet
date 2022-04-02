package com.github.kiolk.feature_toggles.base

interface Toggle {

    fun isEnable(): Boolean

}