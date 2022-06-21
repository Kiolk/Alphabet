package com.github.kiolk.common_di.holder

import com.github.kiolk.common_di.base.BaseComponentHolder
import com.github.kiolk.common_di.base.ClearedComponentHolder
import com.github.kiolk.common_di.base.DIComponent
import java.lang.ref.WeakReference

abstract class FeatureComponentHolder<Component : DIComponent> : BaseComponentHolder<Component>,
    ClearedComponentHolder {

    private var component: WeakReference<Component>? = null


    override fun get(): Component {
        return component?.get() ?: build().also {
            component = WeakReference(it)
        }
    }

    override fun set(component: Component) {
        this.component = WeakReference(component)
    }

    protected abstract fun build(): Component

    override fun clear() {
        component = null
    }
}