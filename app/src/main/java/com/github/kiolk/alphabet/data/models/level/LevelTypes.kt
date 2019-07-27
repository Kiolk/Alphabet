package com.github.kiolk.alphabet.data.models.level

import com.github.kiolk.alphabet.R

enum class LevelTypes(val level: Level, var needStars: Int) {

    DUCK(Level(R.drawable.ic_duck, "Качка"), -1),
    PENGUIN(Level(R.drawable.ic_duck, "Пінгвін"), 5),
    DOG(Level(R.drawable.ic_duck, "Cabaka"), 10),
    LION(Level(R.drawable.ic_duck, "Leu"), 15);

    companion object {
        fun acceptNextLevel(before: Int, after: Int): LevelTypes?{
            return values().firstOrNull{ level -> level.needStars >= before && level.needStars < after}}

        fun getLevel(stars: Int): LevelTypes {
            return values().last{ levelTypes ->  stars > levelTypes.needStars}
        }

        fun getNextLevel(stars: Int): LevelTypes?{
            return values().lastOrNull { levelTypes -> stars < levelTypes.needStars}
        }

        fun getLevel(level: Level): LevelTypes {
            return values().first { type -> type.level == level }
        }
    }
}