package com.github.kiolk.alphabet.presentation.settings.policy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.presentation.base.controller.BaseController

class PolicyController: BaseController,PolicyView {
    @InjectPresenter
    lateinit var presenter: PolicyPresenter

    constructor(): super()
    constructor(args: Bundle) : super(args)

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup): View = inflater.inflate(R.layout.controller_policy, container, false)

    @ProvidePresenter
    fun providePresenter() : PolicyPresenter{
        return getApplicationComponent()
                .plusPolicyPresenter()
                .presenter
    }

    companion object{
        const val TAG ="PolicyController"
    }
}