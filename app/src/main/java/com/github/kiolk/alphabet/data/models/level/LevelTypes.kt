package com.github.kiolk.alphabet.data.models.level

import com.github.kiolk.alphabet.R

enum class LevelTypes(val level: Level, var needStars: Int) {

    DUCK(Level(R.drawable.ic_duck, "Качка"), -1),
    PENGUIN(Level(R.drawable.ic_mankey, "Маўпа"), 0),
    DOG(Level(R.drawable.ic_penguin, "Пінгвін"), 10),
    LION(Level(R.drawable.ic_pug, "Парсючок"), 25);

    companion object {
        fun acceptNextLevel(before: Int, after: Int): LevelTypes?{
            return values().firstOrNull{ level -> level.needStars >= before && level.needStars < after}}

        fun getLevel(stars: Int): LevelTypes {
            return values().lastOrNull{ levelTypes ->  stars > levelTypes.needStars} ?: DUCK
        }

        fun getNextLevel(stars: Int): LevelTypes?{
            val levels = values().filter { levelTypes -> stars <= levelTypes.needStars}
            return if(levels.isNotEmpty()) levels[0] else LION
        }

        fun getLevel(level: Level): LevelTypes {
            return values().first { type -> type.level == level }
        }
    }
}