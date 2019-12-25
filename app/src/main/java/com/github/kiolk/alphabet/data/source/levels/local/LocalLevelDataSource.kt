package com.github.kiolk.alphabet.data.source.levels.local

import com.github.kiolk.alphabet.data.models.level.LevelType
import com.github.kiolk.alphabet.data.source.levels.LevelDataSource
import javax.inject.Inject

class LocalLevelDataSource
@Inject
constructor(private val levelDao: LevelDao): LevelDataSource {

    override fun acceptNextLevel(before: Int, after: Int): LevelType? {
        val levels = levelDao.getAllLevel()
        return levels.firstOrNull { level -> level.needStars > before && level.needStars <= after }
    }

    override fun getLevel(stars: Int): LevelType {
        val levels = levelDao.getAllLevel()
        return levels.lastOrNull{ level ->  stars >= level.needStars} ?: levels[0]
    }

    override fun getNextLevel(stars: Int): LevelType {
        val levels = levelDao.getAllLevel().filter { level -> stars < level.needStars}
        return if(levels.isNotEmpty()) levels[0] else levels.get(levels.size -1)
    }

    override fun addLevels(levels: List<LevelType>) = levelDao.addLevels(levels)

    override fun getAllLevel(): List<LevelType> = levelDao.getAllLevel()
}