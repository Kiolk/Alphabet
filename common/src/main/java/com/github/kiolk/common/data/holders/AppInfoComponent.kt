package com.github.kiolk.common.data.holders

import com.github.kiolk.common.data.model.appInfo.AppInfo
import com.github.kiolk.common_di.base.DIComponent
import com.github.kiolk.common_di.holder.LazyComponentHolder

interface AppInfoComponent : DIComponent {

    fun getAppInfo(): AppInfo
}

object AppInfoComponentHolder : LazyComponentHolder<AppInfoComponent>()