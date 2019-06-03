package com.github.kiolk.alphabet.presentation.home

import android.content.Context
import com.arellomobile.mvp.InjectViewState
import com.github.kiolk.alphabet.data.models.letter.Letter
import com.github.kiolk.alphabet.presentation.base.BasePresenter
import com.github.kiolk.alphabet.utils.CsvParser
import javax.inject.Inject

@InjectViewState
class HomePresenter
@Inject
constructor(private val context : Context, private val letter : Letter): BasePresenter<HomeView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()


        val path = context.assets.list("")[0]

        val words = CsvParser.parserToWords(path ?: "")

        viewState.setTitle(letter.letter)
    }
}