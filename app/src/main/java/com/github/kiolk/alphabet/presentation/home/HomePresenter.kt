package com.github.kiolk.alphabet.presentation.home

import com.arellomobile.mvp.InjectViewState
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.data.domain.words.GetRandomWordUseCase
import com.github.kiolk.alphabet.data.models.letter.Letter
import com.github.kiolk.alphabet.data.models.word.Word
import com.github.kiolk.alphabet.utils.RxSchedulerProvider
import com.github.kiolk.alphabet.utils.selectLetter
import com.github.kiolk.common.presentation.base.BasePresenter
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class HomePresenter
@Inject
constructor(private val letter : Letter,
            private val rxSchedulerProvider: RxSchedulerProvider,
            private val getRandomWordUseCase: GetRandomWordUseCase): BasePresenter<HomeView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        addDisposable(getRandomWordUseCase.execute(GetRandomWordUseCase.Params(letter.letter))
                .compose(rxSchedulerProvider.getIoToMainTransformerSingle())
                .subscribe(this::onGetLetterSuccess, Timber::e))
    }

    private fun onGetLetterSuccess(word: Word){
        viewState.setTitle(letter.letter)
        viewState.setLetterImage(word.image)
        viewState.setExample(selectLetter(word.value.toLowerCase(), letter.letterValue))
    }

    override fun attachView(view: HomeView?) {
        super.attachView(view)
        viewState.setStatusBarColor(R.color.general_dark_blue)
    }
}