package com.github.kiolk.alphabet.presentation.game.result

import com.arellomobile.mvp.InjectViewState
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.data.SoundManager
import com.github.kiolk.alphabet.data.models.game.GameResult
import com.github.kiolk.alphabet.data.source.game.GameRepository
import com.github.kiolk.alphabet.presentation.base.BasePresenter
import javax.inject.Inject

@InjectViewState
class ResultPresenter
@Inject
constructor(private val result: GameResult,
                      private val soundManager: SoundManager,
                      private val gameRepository: GameRepository) : BasePresenter<ResultView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showCorrect("${result.correctAnswers}/${result.gameItems.size}")

        if(result.correctAnswers/ result.gameItems.size > 0.75){
            viewState.showCongratulations(R.string.title_game_result_congratulations)
            viewState.setNextButtonTitle(R.string.text_game_result_next)
        }else{
            viewState.showCongratulations(R.string.title_game_result_sad)
            viewState.setNextButtonTitle(R.string.text_game_result_repeat)
        }
    }

    fun onNextClick(){
        if(result.gameSettings != null) {
            if (result.correctAnswers / result.gameItems.size > 0.75) {
                viewState.closeNext(gameRepository.getNextGame(result.gameSettings!!))
            } else {
                viewState.closeNext(result.gameSettings!!)
            }
        }
    }
}