package com.github.kiolk.alphabet.presentation.base.controller

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.MvpDelegate
import com.bluelinelabs.conductor.Controller

abstract class MoxyController : Controller {

    private val mMvpDelegate: MvpDelegate<MoxyController> by lazy { MvpDelegate(this) }

 protected constructor() : super(){
     mMvpDelegate.onCreate()
 }

    protected constructor(args : Bundle) : super(args){
        mMvpDelegate.onCreate(args)
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        mMvpDelegate.onAttach()
    }

    override fun onDetach(view: View) {
        super.onDetach(view)
        mMvpDelegate.onDetach()
    }

    override fun onDestroyView(view: View) {
        super.onDestroyView(view)
        mMvpDelegate.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        mMvpDelegate.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mMvpDelegate.onSaveInstanceState(outState)
    }
}