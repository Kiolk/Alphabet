package com.github.kiolk.feature_toggles.base

interface Toggle<T> {

    val name: String

    var value: T
}