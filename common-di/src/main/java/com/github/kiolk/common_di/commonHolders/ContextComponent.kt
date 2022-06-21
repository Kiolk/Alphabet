package com.github.kiolk.common_di.commonHolders

import android.content.Context
import com.github.kiolk.common_di.base.DIComponent
import com.github.kiolk.common_di.holder.LazyComponentHolder

interface ContextComponent : DIComponent {

    fun getContext(): Context
}

object ContextComponentHolder : LazyComponentHolder<ContextComponent>()