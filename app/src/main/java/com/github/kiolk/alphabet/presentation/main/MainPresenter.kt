package com.github.kiolk.alphabet.presentation.main

import com.arellomobile.mvp.InjectViewState
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.data.SoundManager
import com.github.kiolk.alphabet.data.domain.player.GetCurrentLevelUseCase
import com.github.kiolk.alphabet.data.domain.player.ResetGameUseCase
import com.github.kiolk.alphabet.data.models.level.LevelViewModel
import com.github.kiolk.alphabet.utils.RxSchedulerProvider
import com.github.kiolk.common.presentation.base.BasePresenter
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class MainPresenter
@Inject
constructor(private val rxSchedulerProvider: RxSchedulerProvider,
            private val getCurrentLevelUseCase: GetCurrentLevelUseCase,
            private val resetGameUseCase: ResetGameUseCase,
            private val soundManager: SoundManager) : BasePresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        addDisposable(getCurrentLevelUseCase.execute((GetCurrentLevelUseCase.Params()))
                .compose(rxSchedulerProvider.goIoToMainTransformerFloweable())
                .subscribe(this::setPlayerLevel) {setPlayerLevelError()})

        setSoundState(soundManager.isOff)
    }

    override fun attachView(view: MainView?) {
        super.attachView(view)
        viewState.setStatusBarColor(R.color.general_gray)
    }

    private fun setPlayerLevelError(){
        viewState.showEdnGameLayout()
    }

    private fun setPlayerLevel(model: LevelViewModel) {
        if(model.isGameEnd){
            viewState.showEdnGameLayout()
            return
        }

        if(model.stars == 0){
//            showWelcomeTour()
        }

        viewState.showLevelImage(model.currentLevel.imageId)
        viewState.showLevelTitle(model.currentLevel.name)
        val levelStart = model.levelStart
        viewState.setLevelStart(levelStart.toString())
        val levelEnd = model.levelEnd
        viewState.setLevelEnd(levelEnd.toString())
        val stars = model.stars
        viewState.setCurrentStars(stars.toString())
        viewState.setSentence(model.currentLevel.sentence)
        viewState.setAuthor(model.currentLevel.author)

        var progress = 0
        levelEnd?.let {

            try {
                progress = (((stars - levelStart) / (it - levelStart).toFloat()) * 100).toInt()
                viewState.setProgress(progress)
            }catch (ex: Exception){
                progress = 100
                viewState.setProgress(progress)
            }
        }

    }

    fun onResetPress() {
        addDisposable(resetGameUseCase.execute(ResetGameUseCase.Params())
                .compose(rxSchedulerProvider.goIoToMainTransformerComplitable())
                .subscribe(this::onResetSuccess, this::onResetError))
    }

    private fun onResetSuccess(){
        viewState.onResetSuccess()
    }

    private fun onResetError(ex: Throwable){
        Timber.e(ex)
    }

    fun onSoundPressed() {
        setSoundState(soundManager.changeSoundState())
    }

    private fun setSoundState(isOff: Boolean) {
        if (isOff) {
            viewState.setSoundState(R.drawable.ic_sound_off)
        } else {
            viewState.setSoundState(R.drawable.ic_sound_on)
        }
    }
}