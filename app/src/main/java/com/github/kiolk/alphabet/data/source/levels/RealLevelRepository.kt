package com.github.kiolk.alphabet.data.source.levels

import com.github.kiolk.common.data.model.level.LevelType
import javax.inject.Inject

class RealLevelRepository
@Inject
constructor(private val local: LevelDataSource): LevelRepository {

    override fun acceptNextLevel(before: Int, after: Int): LevelType? = local.acceptNextLevel(before, after)

    override fun getLevel(stars: Int): LevelType = local.getLevel(stars)

    override fun getNextLevel(stars: Int): LevelType = local.getNextLevel(stars)

    override fun addLevels(levels: List<LevelType>) = local.addLevels(levels)

    override fun getAllLevel(): List<LevelType> = local.getAllLevel()
}