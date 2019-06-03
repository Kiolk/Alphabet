package com.github.kiolk.alphabet.di.compomemts.presenters

import com.github.kiolk.alphabet.di.scopes.Presenter
import com.github.kiolk.alphabet.presentation.words.WordPresentor
import dagger.Subcomponent

@Presenter
@Subcomponent
interface WordsPresenterComponent {

    val presenter : WordPresentor
}