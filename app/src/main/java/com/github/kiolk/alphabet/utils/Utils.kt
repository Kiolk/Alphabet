package com.github.kiolk.alphabet.utils

fun numberOfStars(correct: Int, total: Int): Int{
    val percents = correct / total
    return when {
        percents < 0.5 -> 0
        percents < 0.7 -> 1
        percents < 0.9 -> 2
        else -> 3
    }
}