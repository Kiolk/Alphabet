package com.github.kiolk.alphabet.data.models.level

import com.github.kiolk.alphabet.R

enum class LevelTypes(val level: Level, var needStars: Int) {

    DUCK(Level(R.drawable.ic_duck, "Качка"), 1),
    PENGUIN(Level(R.drawable.ic_duck, "Пінгвін"), 5),
    DOG(Level(R.drawable.ic_duck, "Cabaka"), 10),
    LION(Level(R.drawable.ic_duck, "Leu"), 15);

    companion object {
        fun acceptNextLevel(before: Int, after: Int): LevelTypes?{
            return values().firstOrNull{ level -> level.needStars > before && level.needStars < after}}

        fun getLevel(stars: Int): Level {
            return values().first{ levelTypes ->  levelTypes.needStars > stars}.level
        }
    }
}