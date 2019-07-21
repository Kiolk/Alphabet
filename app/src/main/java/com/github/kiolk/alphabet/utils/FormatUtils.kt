package com.github.kiolk.alphabet.utils

import android.graphics.Color
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan

fun selectLetter(text: String, letter: String): SpannableStringBuilder{
    val spannableBuilder = SpannableStringBuilder(text)
    val indexes = mutableListOf<Int>()

    text.filterIndexed { index, c ->
        if(c.toString() == letter){
            indexes.add(index)
        }
        c.toString() == letter
    }

    indexes.forEach { index ->
        spannableBuilder.setSpan(
                ForegroundColorSpan(Color.RED),
                index,
                index + 1,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }

    return spannableBuilder
}