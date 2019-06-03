package com.github.kiolk.alphabet.presentation.game.preview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.presentation.base.controller.BaseController

class GamePreviewControlelr : BaseController {

    constructor() : super()
    constructor(args: Bundle) : super(args)

    @InjectPresenter
    lateinit var presenter: GamePreviewPresenter

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup): View = inflater.inflate(R.layout.controller_game_previe, container, false)

    @ProvidePresenter
    fun providePresenter(): GamePreviewPresenter {
        return getApplicationComponent()
                .plusGamePreviewComponent()
                .presenter
    }
}