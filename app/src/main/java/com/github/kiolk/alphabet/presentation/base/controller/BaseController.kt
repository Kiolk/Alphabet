package com.github.kiolk.alphabet.presentation.base.controller

import android.os.Build
import android.os.Bundle
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.github.kiolk.alphabet.App
import com.github.kiolk.alphabet.di.compomemts.ApplicationComponent
import com.github.kiolk.alphabet.presentation.dialogs.CompleteLevelDialog
import com.github.kiolk.alphabet.presentation.words.WordsScreen
import com.github.kiolk.common.data.model.level.LevelType
import com.github.kiolk.common.presentation.base.BaseView
import com.github.kiolk.common.presentation.controller.ButterknifeController

abstract class BaseController : ButterknifeController, BaseView {

    protected constructor() : super()
    protected constructor(args : Bundle) : super(args)

    protected fun getApplicationComponent() : ApplicationComponent = App.component

    override fun showCompleteLevelDialog(level: LevelType) {
        val dialog = CompleteLevelDialog.getInstance(level)

        router.pushController(RouterTransaction.with(dialog)
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler()))
    }

    override fun pop() {
        router.popCurrentController()
    }

    override fun setStatusBarColor(coloRes: Int){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            (activity as? WordsScreen)?.setStatusBarColor(coloRes)
        }
    }
}