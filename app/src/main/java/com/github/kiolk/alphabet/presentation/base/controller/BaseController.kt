package com.github.kiolk.alphabet.presentation.base.controller

import android.os.Bundle
import com.github.kiolk.alphabet.App
import com.github.kiolk.alphabet.di.compomemts.ApplicationComponent
import com.github.kiolk.alphabet.presentation.base.BaseView

abstract class BaseController : ButterknifeController, BaseView {

    protected constructor() : super()
    protected constructor(args : Bundle) : super(args)

    protected fun getApplicationComponent() : ApplicationComponent = App.component
}