package com.github.kiolk.alphabet.presentation.words

import com.arellomobile.mvp.MvpView
import com.github.kiolk.alphabet.data.models.game.GameSettings
import com.github.kiolk.alphabet.data.models.letter.Letter
import com.github.kiolk.alphabet.data.models.word.Word

interface WordsView : MvpView {

    fun setAvailableTopics(topics : List<GameSettings>)

    fun closeMenu()

    fun setAlphabet(alphabet : List<Letter>)

    fun setSelectedLetter(letter : Letter)
}