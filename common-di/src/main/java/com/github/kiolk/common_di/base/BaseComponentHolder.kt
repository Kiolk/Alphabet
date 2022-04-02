package com.github.kiolk.common_di.base

interface BaseComponentHolder<Component : DIComponent> {

    fun get(): Component

    fun set(component: Component)
}