package com.github.kiolk.alphabet.presentation.game.preview

import com.arellomobile.mvp.InjectViewState
import com.github.kiolk.alphabet.data.domain.PrepareGameSetUseCase
import com.github.kiolk.alphabet.data.models.game.GameSettings
import com.github.kiolk.alphabet.data.source.words.WordsRepository
import com.github.kiolk.alphabet.presentation.base.BasePresenter
import com.github.kiolk.alphabet.utils.RxSchedulerProvider
import javax.inject.Inject

@InjectViewState
class GamePreviewPresenter
@Inject constructor(private val gameSettings: GameSettings,
                    private val repository: WordsRepository,
                    private val rxSchedulerProvider: RxSchedulerProvider,
                    private val prepareGameSetUseCase: PrepareGameSetUseCase) : BasePresenter<GamePreviewView>() {



}