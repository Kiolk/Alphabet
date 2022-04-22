package com.github.kiolk.feature_toggles.base

interface Config<T> {

    val name: String

    val value: T
}