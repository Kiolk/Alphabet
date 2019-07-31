package com.github.kiolk.alphabet.data.models.level

import com.github.kiolk.alphabet.R

enum class LevelTypes(val level: Level, var needStars: Int) {

    DUCK(Level(R.drawable.ic_duck, "Качка"), -1),
    PENGUIN(Level(R.drawable.ic_penguin, "Пінгвін"), 0),
    DOG(Level(R.drawable.ic_dog, "Сабака"), 10),
    PIG(Level(R.drawable.ic_pug, "Парсючок"), 25),
    MANKEY(Level(R.drawable.ic_mankey, "Маўпа"), 25),
    BEAVER(Level(R.drawable.ic_beaver, "Бабер"), 25),
    CHIKEN(Level(R.drawable.ic_chiken, "Кураня"), 25),
    COCK(Level(R.drawable.ic_cock, "Пеўнік"), 25),
    WHITE_BEAR(Level(R.drawable.ic_white_bear, "Белы мядзведзь"), 25),
    ARCTIC_FOX(Level(R.drawable.ic_arctc_fox, "Пясец"), 25),
    COW(Level(R.drawable.ic_cow, "Карова"), 25),
    DONKEY(Level(R.drawable.ic_donkey, "Вослік"), 25),
    ELEPHANT(Level(R.drawable.ic_elephant, "Слон"), 25),
    WARLUS(Level(R.drawable.ic_walrus, "Морж"), 25);

    companion object {
        fun acceptNextLevel(before: Int, after: Int): LevelTypes?{
            return values().firstOrNull{ level -> level.needStars > before && level.needStars <= after}}

        fun getLevel(stars: Int): LevelTypes {
            return values().lastOrNull{ levelTypes ->  stars >= levelTypes.needStars} ?: DUCK
        }

        fun getNextLevel(stars: Int): LevelTypes?{
            val levels = values().filter { levelTypes -> stars < levelTypes.needStars}
            return if(levels.isNotEmpty()) levels[0] else WARLUS
        }

        fun getLevel(level: Level): LevelTypes {
            return values().first { type -> type.level == level }
        }
    }
}