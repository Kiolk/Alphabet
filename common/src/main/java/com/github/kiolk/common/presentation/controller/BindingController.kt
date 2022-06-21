package com.github.kiolk.common.presentation.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BindingController : MoxyController {

    protected constructor() : super()
    protected constructor(args: Bundle) : super(args)

    protected abstract fun inflateView(inflater: LayoutInflater, container: ViewGroup): View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflateView(inflater, container)
        //TODO logic for Binding
        return view
    }
}