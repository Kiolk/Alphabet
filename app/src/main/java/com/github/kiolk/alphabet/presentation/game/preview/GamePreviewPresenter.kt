package com.github.kiolk.alphabet.presentation.game.preview

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.github.kiolk.alphabet.data.domain.PrepareGameSetUseCase
import com.github.kiolk.alphabet.data.models.game.GameItem
import com.github.kiolk.alphabet.data.models.game.GameResult
import com.github.kiolk.alphabet.data.models.game.GameSettings
import com.github.kiolk.alphabet.data.models.word.Word
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

    private lateinit var gameSet : Array<GameItem>

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        addDisposable(repository.selectWords(gameSettings)
                .compose(rxSchedulerProvider.goIoToMainTransformerFloweable())
                .subscribe(this::onWordSuccess, this::onError))
    }

    fun onError(throwable: Throwable) {
        Log.d("MyLogs", throwable.message)
    }

    fun onWordSuccess(wordList: List<Word>) {
//        gameSet = prepareGameSetUseCase.execute(PrepareGameSetUseCase.Params(gameSettings, wordList)).toTypedArray()
    }

    fun onStartClick() {
//        viewState.startGame(GameResult(gameSettings, gameSet, 0, 0))
    }
}