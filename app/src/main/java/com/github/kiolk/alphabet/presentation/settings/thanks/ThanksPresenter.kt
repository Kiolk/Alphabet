package com.github.kiolk.alphabet.presentation.settings.thanks

import com.arellomobile.mvp.InjectViewState
import com.github.kiolk.alphabet.data.domain.words.GetAllImageAuthorsUseCase
import com.github.kiolk.alphabet.utils.RxSchedulerProvider
import com.github.kiolk.common.presentation.base.BasePresenter
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class ThanksPresenter
@Inject
constructor(private val getAllImageAuthorsUseCase: GetAllImageAuthorsUseCase,
            private val rxSchedulerProvider: RxSchedulerProvider) : BasePresenter<ThanksView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        addDisposable(getAllImageAuthorsUseCase.execute(GetAllImageAuthorsUseCase.Params())
                .compose(rxSchedulerProvider.getIoToMainTransformerSingle())
                .subscribe(this::onAuthorsSuccess, Timber::e))
    }

    private fun onAuthorsSuccess(authors: List<String>){
        viewState.hideProgress()
        viewState.showAuthors(authors)
    }
}