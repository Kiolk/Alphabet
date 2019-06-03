package com.github.kiolk.alphabet.presentation.words

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.github.kiolk.alphabet.data.models.game.GameSettings

@StateStrategyType(AddToEndSingleStrategy::class)
interface MenuListenerView : MvpView{

    fun enableSyllable(enable : Boolean)

    fun enableSentence(enable  : Boolean)

    fun setTopic(gameSettings: GameSettings)
}