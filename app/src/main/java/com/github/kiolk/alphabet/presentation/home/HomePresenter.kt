package com.github.kiolk.alphabet.presentation.home

import com.arellomobile.mvp.InjectViewState
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.data.models.letter.Letter
import com.github.kiolk.alphabet.presentation.base.BasePresenter
import com.github.kiolk.alphabet.utils.selectLetter
import javax.inject.Inject

@InjectViewState
class HomePresenter
@Inject
constructor(private val letter : Letter): BasePresenter<HomeView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setTitle(letter.letter)
        viewState.setLetterImage("https://avatars.mds.yandex.net/get-pdb/1662775/e8d48978-4d45-450d-ba01-635328d96340/s1200?webp=false")//(letter.image)
        viewState.setExample(selectLetter(letter.letterWord.toLowerCase(), letter.letterValue))
    }

    override fun attachView(view: HomeView?) {
        super.attachView(view)
        viewState.setStatusBarColor(R.color.general_dark_blue)
    }
}