package com.github.kiolk.alphabet.presentation.main

import com.arellomobile.mvp.InjectViewState
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.data.domain.player.GetCurrentLevelUseCase
import com.github.kiolk.alphabet.data.models.level.LevelViewModel
import com.github.kiolk.alphabet.presentation.base.BasePresenter
import com.github.kiolk.alphabet.utils.RxSchedulerProvider
import java.lang.Exception
import javax.inject.Inject

@InjectViewState
class MainPresenter
@Inject
constructor(private val rxSchedulerProvider: RxSchedulerProvider,
            private val getCurrentLevelUseCase: GetCurrentLevelUseCase) : BasePresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        addDisposable(getCurrentLevelUseCase.execute((GetCurrentLevelUseCase.Params()))
                .compose(rxSchedulerProvider.goIoToMainTransformerFloweable())
                .subscribe(this::setPlayerLevel))
    }

    override fun attachView(view: MainView?) {
        super.attachView(view)
        viewState.setStatusBarColor(R.color.general_gray)
    }

    private fun setPlayerLevel(model: LevelViewModel) {
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
}