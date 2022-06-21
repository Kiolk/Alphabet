package com.github.kiolk.alphabet.data.models.level

import com.github.kiolk.common.data.model.level.LevelType

data class LevelViewModel(
    val stars: Int,
    val levelStart: Int,
    val levelEnd: Int?,
    val currentLevel: LevelType,
    var isGameEnd: Boolean = false
)