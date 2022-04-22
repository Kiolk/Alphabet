package com.github.kiolk.common_di.holder

import com.github.kiolk.common_di.base.BaseComponentHolder
import com.github.kiolk.common_di.base.ClearedComponentHolder
import com.github.kiolk.common_di.base.DIComponent

abstract class LazyComponentHolder<Component : DIComponent> : BaseComponentHolder<Component>,
    ClearedComponentHolder {

    @Volatile
    private var component: Component? = null
    private var componentProvider: () -> Component = { throw IllegalStateException() }

    override fun get(): Component {
        return component ?: synchronized(this) {
            component ?: componentProvider().also { set(it) }
        }
    }

    override fun set(component: Component) {
        this.component = component
    }

    fun set(provider: () -> Component) {
        componentProvider = provider
    }

    override fun clear() {
        componentProvider = { throw IllegalStateException() }
    }
}