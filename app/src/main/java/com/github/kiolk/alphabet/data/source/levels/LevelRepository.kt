package com.github.kiolk.alphabet.data.source.levels

import com.github.kiolk.common.data.model.level.LevelType

interface LevelRepository {

    fun acceptNextLevel(before: Int, after: Int): LevelType?

    fun getLevel(stars: Int): LevelType

    fun getNextLevel(stars: Int): LevelType

    fun addLevels(levels: List<LevelType>)

    fun getAllLevel(): List<LevelType>
}