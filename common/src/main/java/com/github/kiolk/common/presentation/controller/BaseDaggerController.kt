package com.github.kiolk.common.presentation.controller

import android.os.Bundle
import com.github.kiolk.common.presentation.base.BasePresenter
import com.github.kiolk.common.presentation.base.BaseView

abstract class BaseDaggerController<V : BaseView, out T : BasePresenter<V>> : BindingController,
    BaseView {

    protected constructor() : super()
    protected constructor(args: Bundle) : super(args)

    protected abstract fun preparePresenter(): T

//    @ProvidePresenter
//    fun providePresenter(): T {
//        return preparePresenter()
//    }

    override fun pop() {
        router.popCurrentController()
    }
}