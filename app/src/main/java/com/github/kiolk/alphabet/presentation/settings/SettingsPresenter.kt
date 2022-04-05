package com.github.kiolk.alphabet.presentation.settings

import com.arellomobile.mvp.InjectViewState
import com.github.kiolk.alphabet.data.domain.player.ResetGameUseCase
import com.github.kiolk.alphabet.utils.RxSchedulerProvider
import com.github.kiolk.common.presentation.base.BasePresenter
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class SettingsPresenter
@Inject
constructor(private val resetGameUseCase: ResetGameUseCase, private val rxSchedulerProvider: RxSchedulerProvider) : BasePresenter<SettingsView>() {
    fun onResetPress() {
        addDisposable(resetGameUseCase.execute(ResetGameUseCase.Params())
                .compose(rxSchedulerProvider.goIoToMainTransformerComplitable())
                .subscribe(this::onResetSuccess, this::onResetError))
    }

    private fun onResetSuccess(){
        viewState.openMainScreen()
    }

    private fun onResetError(ex: Throwable){
        Timber.e(ex)
    }
}