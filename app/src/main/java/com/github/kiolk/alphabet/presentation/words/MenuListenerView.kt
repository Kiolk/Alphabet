package com.github.kiolk.alphabet.presentation.words

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.github.kiolk.alphabet.data.models.game.GameSettings
import com.github.kiolk.alphabet.data.models.topic.Topic

@StateStrategyType(AddToEndSingleStrategy::class)
interface MenuListenerView : MvpView{

    fun onTopicClick(gameSettings: GameSettings)

    fun onWordsTopicClick(topic: Topic)
}