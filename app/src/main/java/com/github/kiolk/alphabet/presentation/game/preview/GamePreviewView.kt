package com.github.kiolk.alphabet.presentation.game.preview

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.github.kiolk.alphabet.data.models.game.GameResult
import com.github.kiolk.common.presentation.base.BaseView

@StateStrategyType(AddToEndSingleStrategy::class)
interface GamePreviewView: BaseView {

    fun setGameImage(image: String)

    fun setGamePhotos(image: List<String>)

    fun setTitle(title: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun startGame(result: GameResult)

}