package com.github.kiolk.alphabet.presentation.words

import com.arellomobile.mvp.MvpView
import com.github.kiolk.alphabet.data.models.game.GameSettings
import com.github.kiolk.alphabet.data.models.letter.Letter
import com.github.kiolk.alphabet.data.models.word.Word

interface WordsView : MvpView {

    fun setWord(word: String)

    fun setSyllableEnable(enable : Boolean)

    fun setSentanceEnable(enable : Boolean)

    fun setAvailableTopics(topics : List<GameSettings>)

    fun closeMenu()

    fun setWordPictures( list: List<Word>)

    fun setAnswer(userAnswer : Word, correct : Word)

    fun enableNextButton()

    fun disableNext()

    fun setAlphabet(alphabet : List<Letter>)

    fun setSelectedLetter(letter : Letter)
}