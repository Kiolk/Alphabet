package com.github.kiolk.alphabet.presentation.base.controller

import android.os.Bundle
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.github.kiolk.alphabet.App
import com.github.kiolk.alphabet.data.models.level.Level
import com.github.kiolk.alphabet.di.compomemts.ApplicationComponent
import com.github.kiolk.alphabet.presentation.base.BaseView
import com.github.kiolk.alphabet.presentation.dialogs.CompleteLevelDialog

abstract class BaseController : ButterknifeController, BaseView {

    protected constructor() : super()
    protected constructor(args : Bundle) : super(args)

    protected fun getApplicationComponent() : ApplicationComponent = App.component

    override fun showCompleteLevelDialog(level: Level) {
        val dialog = CompleteLevelDialog.getInstance(level)

        router.pushController(RouterTransaction.with(dialog)
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler()))
    }
}