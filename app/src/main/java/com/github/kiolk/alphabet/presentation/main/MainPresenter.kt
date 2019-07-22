package com.github.kiolk.alphabet.presentation.main

import com.arellomobile.mvp.InjectViewState
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.data.domain.player.GetCurrentLevelUseCase
import com.github.kiolk.alphabet.data.models.level.Level
import com.github.kiolk.alphabet.presentation.base.BasePresenter
import com.github.kiolk.alphabet.utils.RxSchedulerProvider
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

    private fun setPlayerLevel(levle: Level){
        viewState.showLevelImage(levle.image)
        viewState.showLevelTitle(levle.title)
    }
}