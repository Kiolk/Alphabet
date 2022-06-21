package com.github.kiolk.alphabet.presentation.game.result

import com.arellomobile.mvp.InjectViewState
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.data.domain.UpdateGameUseCase
import com.github.kiolk.alphabet.data.models.game.GameResult
import com.github.kiolk.alphabet.data.models.throwables.NoGameSettings
import com.github.kiolk.alphabet.data.source.settings.SettingsRepository
import com.github.kiolk.alphabet.utils.RxSchedulerProvider
import com.github.kiolk.common.data.model.word.GameSettings
import com.github.kiolk.common.presentation.base.BasePresenter
import javax.inject.Inject

@InjectViewState
class ResultPresenter
@Inject
constructor(private val result: GameResult,
            private val settingsRepository: SettingsRepository,
            private val updateGameUseCase: UpdateGameUseCase,
            private val rxSchedulerProvider: RxSchedulerProvider) : BasePresenter<ResultView>() {

    private lateinit var game: GameSettings

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showCorrect("${result.correctAnswers}/${result.gameItems.size}")
        val isCompleted = result.correctAnswers / result.gameItems.size > 0.75
        if (isCompleted) {
            viewState.showCongratulations(R.string.title_game_result_congratulations)
            viewState.setNextButtonTitle(R.string.text_game_result_next)
//            result.gameSettings.isCompleted = isCompleted

//            addDisposable(updateGameUseCase.execute(UpdateGameUseCase.Params(result.gameSettings))
//                    .compose(rxSchedulerProvider.getIoToMainTransformerSingle())
//                    .subscribe(this::onNextAvailableGame, this::onErrorAvailableGame))
        } else {
            viewState.showCongratulations(R.string.title_game_result_sad)
            viewState.setNextButtonTitle(R.string.text_game_result_repeat)
            viewState.setNextButtonEnable()
//            game = result.gameSettings
        }
    }

    fun onNextAvailableGame(gameSetting: GameSettings){
        viewState.setNextButtonEnable()
        game = gameSetting
        game.isAvailable = true
        addDisposable(settingsRepository.updateSetting(game)
                .compose(rxSchedulerProvider.goIoToMainTransformerComplitable())
                .subscribe(this::onNewGameAvailable, this::onErrorNewGameAvailable))
    }

    fun onErrorAvailableGame(throwable: Throwable){
        if(throwable is NoGameSettings){

        }else{

        }
    }

    fun onNewGameAvailable(){

    }

    fun onErrorNewGameAvailable(throwable: Throwable){

    }

    fun onNextClick() {
        viewState.closeNext(game)
    }
}