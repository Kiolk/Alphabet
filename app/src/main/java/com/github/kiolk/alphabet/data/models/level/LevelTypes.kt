package com.github.kiolk.alphabet.data.models.level

import com.github.kiolk.alphabet.R

enum class LevelTypes(val level: Level, var needStars: Int, val sentence: String, val  author: String) {

    DUCK(Level(R.drawable.ic_duck, "Качка"), -1, "Каб любіць Беларусь нашу мілую,\nТрэба ў розных краях пабываць.", "N.R.M."),
    PENGUIN(Level(R.drawable.ic_penguin, "Пінгвін"), 0, "Не будзь ласы на чужыя каўбасы.", "Прыказка"),
    DOG(Level(R.drawable.ic_dog, "Сабака"), 10, "У Савосева суседа\nБыў пярэсценькі каток,\nВыхаванец Паўла-дзеда,\nТакі слаўны пестунок!", "Якуб Колас"),
    PIG(Level(R.drawable.ic_pug, "Парсючок"), 25, "Зорка Венера ўзышла над зямлёю,\nСветлыя згадкi з сабой прывяла...", "Максім Багдановіч"),
    MANKEY(Level(R.drawable.ic_mankey, "Маўпа"), 25, "Ад прадзедаў спакон вякоў\nМне засталася спадчына;", "Янка Купала"),
    BEAVER(Level(R.drawable.ic_beaver, "Бабер"), 25, "Але лёс склаўся так... што хрусь - і папалам", "N.R.M."),
    CHIKEN(Level(R.drawable.ic_chiken, "Кураня"), 25, "Не сядзіцца ў хаце\nХлопчыку малому:\nКліча яго рэчка,\nЦягнуць санкі з дому...", "Якуб Колас"),
    COCK(Level(R.drawable.ic_cock, "Пеўнік"), 25, "Не пакідайце ж мовы нашай беларускай, каб не ўмёрлі.", "Францішак Багушэвіч"),
    WHITE_BEAR(Level(R.drawable.ic_white_bear, "Белы мядзведзь"), 25, "Рабі нечаканае, рабі, як не бывае, рабі, як не робіць ніхто, – і тады пераможаш.", "Уладзімір Караткевіч"),
    ARCTIC_FOX(Level(R.drawable.ic_arctc_fox, "Пясец"), 25, "Трэба дома бываць часцей,\nТрэба дома бываць не госцем,", "Рыгор Барадулін"),
    COW(Level(R.drawable.ic_cow, "Карова"), 25, "Другі баран — ні «бэ», ні «мя»,\nА любіць гучнае імя.", "Кандрат Крапіва"),
    DONKEY(Level(R.drawable.ic_donkey, "Вослік"), 25, "Абяцанкi-цацанкi, а дурному радасць.", "Прымаўка"),
    ELEPHANT(Level(R.drawable.ic_elephant, "Слон"), 25, "Хто ты гэткі?\n—  Свой, тутэйшы.\nЧаго хочаш?\n—  Долі лепшай.", "Янка Купала"),
    WARLUS(Level(R.drawable.ic_walrus, "Морж"), 25, "– Каго любіш?\n– Люблю Беларусь!\n– То ўзаемна", "Кастусь Каліноўскі");
}