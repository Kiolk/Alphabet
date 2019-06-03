package com.github.kiolk.alphabet.presentation

import com.github.kiolk.alphabet.R

enum class WordsSet(val title : String, val resId  : Int, val query : String = "") {

    SYLLABLE("Слагі", R.drawable.syllable, "___"),
    THREE_LATERS_WORDS("Тры", R.drawable.three, "____"),
    FOR_CHAR_WORDS("Чатыры", R.drawable.fore, "_____"),
    SHORT_WORDS("Кароткія словы", R.drawable.slova, "______"),
    FOUR_ONE_VOWEL("Адна сярод", R.drawable.one_from, "_______" ),
    FIVE_CHAR("Пяць", R.drawable.five, ""),
    SIX_CHAR("Шэсць", R.drawable.six, "_________")
}