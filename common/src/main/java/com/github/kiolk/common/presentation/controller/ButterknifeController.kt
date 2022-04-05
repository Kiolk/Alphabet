package com.github.kiolk.common.presentation.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.Unbinder

abstract class ButterknifeController : MoxyController {

    private var unbinder: Unbinder? = null

    protected constructor() : super()
    protected constructor(args: Bundle) : super(args)

    protected abstract fun inflateView(inflater: LayoutInflater, container: ViewGroup): View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflateView(inflater, container)
        unbinder = ButterKnife.bind(this, view)
        onViewBind(view)
        return view
    }

    override fun onDestroyView(view: View) {
        unbinder?.unbind()
        unbinder = null
        super.onDestroyView(view)
    }

    protected open fun onViewBind(itemVie: View) {}
}