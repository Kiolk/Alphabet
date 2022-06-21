package com.github.kiolk.alphabet.utils

import com.github.kiolk.alphabet.data.models.game.GameSettingBuilder
import com.github.kiolk.alphabet.data.models.letter.Letter
import com.github.kiolk.common.data.model.word.Word

object Data{
    val alphabet = listOf(Letter("а", "а", "d", letterWord = "Аўтобус"),
            Letter("б", "б", "d", letterWord = "Бусел"),
            Letter("в", "в", "d", letterWord = "Вуха"),
            Letter("г", "г", "d", letterWord = "Грыб"),
            Letter("д", "д", "d", letterWord = "Дрэва"),
            Letter("дж", "дж", "d", letterWord = "Джала"),
            Letter("дз", "дз", "d", letterWord = "Дзяцел"),
            Letter("е", "е", "d", letterWord = "Елка"),
            Letter("ё", "ё", "d", letterWord = "Аўтобус"),
            Letter("ж", "ж", "d", letterWord = "Жарабя"),
            Letter("з", "з", "d", letterWord = "Зерне"),
            Letter("і", "і", "d", letterWord = "Іклы"),
            Letter("й", "й", "d", letterWord = "Йод"),
            Letter("к", "к", "d", letterWord = "Крэйда"),
            Letter("л", "л", "d", letterWord = "Ліхтар"),
            Letter("м", "м", "d", letterWord = "Месяц"),
            Letter("н", "н", "d", letterWord = "Нага"),
            Letter("о", "о", "d", letterWord = "Ордэн"),
            Letter("п", "п", "d", letterWord = "Парасон"),
            Letter("р", "р", "d", letterWord = "Рамонак"),
            Letter("с", "с", "d", letterWord = "Сонца"),
            Letter("т", "т", "d", letterWord = "Трактар"),
            Letter("у", "у", "d", letterWord = "Укол"),
            Letter("ў", "ў", "d", letterWord = "Аўтобус"),
            Letter("ф", "ф", "d", letterWord = "Футра"),
            Letter("х", "х", "d", letterWord = "Хлеб"),
            Letter("ц", "ц", "d", letterWord = "Цацка"),
            Letter("ч", "ч", "d", letterWord = "Чаравік"),
            Letter("ш", "ш", "d", letterWord = "Шкло"),
            Letter("ы", "ы", "d", letterWord = "Рыба"),
            Letter("ь", "ь", "d", letterWord = "Соль"),
            Letter("э", "э", "d", letterWord = "Эскалатар"),
            Letter("ю", "ю", "d", letterWord = "Юла"),
            Letter("я", "я", "d", letterWord = "Якар")
    )

    val gameSettingsPatterns: List<GameSettingBuilder> = listOf<GameSettingBuilder>(
            GameSettingBuilder().setTitle("Пач").setLettersInWord(3).setPosition(GameSettingBuilder.Position.ANY).setGameLevel(0),
            GameSettingBuilder().setTitle("Пачатак").setPosition(GameSettingBuilder.Position.START).setGameLevel(1),
            GameSettingBuilder().setTitle("Канец").setPosition(GameSettingBuilder.Position.END).setGameLevel(2),
            GameSettingBuilder().setTitle("Сяпэдзіна").setPosition(GameSettingBuilder.Position.INSIDE).setGameLevel(3),
            GameSettingBuilder().setTitle("Дзве").setNumberOfLetters(2).setPosition(GameSettingBuilder.Position.ANY).setGameLevel(4),
            GameSettingBuilder().setTitle("Тры").setNumberOfLetters(3).setPosition(GameSettingBuilder.Position.ANY).setGameLevel(5),
            GameSettingBuilder().setTitle("Чатыры").setNumberOfLetters(4).setPosition(GameSettingBuilder.Position.ANY).setGameLevel(6)
//            GameSettingBuilder().setTitle("Тры").setLettersInWord(3).setPosition(GameSettingBuilder.Position.ANY).setGameLevel(1),
//            GameSettingBuilder().setTitle("Чатыры").setLettersInWord(4).setPosition(GameSettingBuilder.Position.ANY).setGameLevel(2),
//            GameSettingBuilder().setTitle("Пяць").setLettersInWord(5).setPosition(GameSettingBuilder.Position.ANY).setGameLevel(3),
//            GameSettingBuilder().setTitle("Шэсць").setLettersInWord(6).setPosition(GameSettingBuilder.Position.ANY).setGameLevel(4),
//            GameSettingBuilder().setTitle("Літара").setPosition(GameSettingBuilder.Position.ANY).setGameLevel(5),
//            GameSettingBuilder().setTitle("Пачатак").setPosition(GameSettingBuilder.Position.START).setGameLevel(6)
    )

    val testSetOfWord = listOf<Word>(
            Word("жук", "жук", "https://upload.wikimedia.org/wikipedia/commons/0/0e/Dynastes_hercules_ecuatorianus_MHNT.jpg", "жывёлы"),
            Word("мак", "мак", "https://upload.wikimedia.org/wikipedia/commons/7/71/Poppies_in_the_Sunset_on_Lake_Geneva.jpg", "расліны"),

            Word("вада", "ва-да", "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a6/Waterdrops_%284648726722%29.jpg/800px-Waterdrops_%284648726722%29.jpg", "рэчыва"),
            Word("тыгр", "тыгр", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/da/Panthera_tigris_tigris_Tidoba_20150306.jpg/1280px-Panthera_tigris_tigris_Tidoba_20150306.jpg", "рэчыва"),
            Word("слон", "слон", "https://upload.wikimedia.org/wikipedia/commons/3/37/African_Bush_Elephant.jpg", "жывёлы"),
            Word("снег", "снег", "https://upload.wikimedia.org/wikipedia/commons/e/ed/Sparkling-snow.crystals.jpg", "жывёлы"),
            Word("кола", "ко-ла", "https://upload.wikimedia.org/wikipedia/commons/3/32/Tekerlek.jpg", "рэчыва"),
            Word("мука", "му-ка", "https://upload.wikimedia.org/wikipedia/commons/6/64/All-Purpose_Flour_%284107895947%29.jpg", "рэчыва"),
            Word("куля", "ку-ля", "https://upload.wikimedia.org/wikipedia/commons/6/65/Halvmantlad.jpg", "рэчыва"),
            Word("рука", "ру-ка", "https://upload.wikimedia.org/wikipedia/commons/b/bf/Handskelett.png", "рэчыва"),
            Word("цень", "цень", "https://upload.wikimedia.org/wikipedia/commons/3/33/Looking_down_from_The_Eiffel_Tower%2C_Paris_8_April_2007.jpg", "рэчыва"),
            Word("рыба", "ры-ба", "https://upload.wikimedia.org/wikipedia/commons/2/23/Georgia_Aquarium_-_Giant_Grouper_edit.jpg", "жывёлы"),

            Word("чабор", "ча-бор", "https://upload.wikimedia.org/wikipedia/commons/e/ee/%D0%9A%D0%B2%D0%B5%D1%82%D0%BA%D1%96_%D1%87%D0%B0%D0%B1%D0%BE%D1%80%D0%B0.jpg", "расліны"),
            Word("жолуд", "жо-луд", "https://upload.wikimedia.org/wikipedia/commons/d/d8/Acorns_small_to_large.jpg", "рэчыва"),
            Word("месяц", "ме-сяц", "https://upload.wikimedia.org/wikipedia/commons/e/e1/FullMoon2010.jpg", "рэчыва"),
            Word("човен", "чо-вен", "https://upload.wikimedia.org/wikipedia/commons/5/50/Do%C5%82banka.jpg", "рэчыва"),
            Word("чарот", "ча-рот", "https://upload.wikimedia.org/wikipedia/commons/1/1d/ScirpusSylvaticus2.jpg", "расліны"),
            Word("вецер", "ве-цер", "https://upload.wikimedia.org/wikipedia/commons/e/e2/Cherry_tree_moving_in_the_wind_1.gif", "рэчыва"),
            Word("чмель", "чмель", "https://upload.wikimedia.org/wikipedia/commons/4/49/Bumblebee_heuchera.jpg", "жывёлы"),

            Word("малако", "ма-ла-ко", "https://upload.wikimedia.org/wikipedia/commons/0/0e/Milk_glass.jpg", "рэчыва"),
            Word("чарвяк", "чар-вяк", "https://upload.wikimedia.org/wikipedia/commons/3/30/Regenwurm1.jpg", "жывёлы"),
            Word("бульба", "буль-ба", "https://upload.wikimedia.org/wikipedia/commons/0/09/Bamberger_Hoernle.jpg", "рэчыва"),
            Word("камень", "ка-мень", "https://upload.wikimedia.org/wikipedia/commons/a/aa/GabbroRockCreek1.jpg", "рэчыва"),

            Word("чаравік", "ча-ра-вік", "https://upload.wikimedia.org/wikipedia/commons/c/cc/S3_safety_footwear.jpg", "рэчыва"),
            Word("лес", "лес", "https://upload.wikimedia.org/wikipedia/commons/a/a0/Poland_Bialowieza_-_BPN.jpg", "прырода"),
            Word("нос", "нос", "https://upload.wikimedia.org/wikipedia/commons/d/d0/Canine-nose.jpg", "цела"),
            Word("бэз", "бэз", "https://upload.wikimedia.org/wikipedia/commons/a/ad/Syringa_%27Pamyat_o_Vekhove%27_01.jpg", "кветкі, расліны"),
            Word("дым", "дым", "https://upload.wikimedia.org/wikipedia/commons/f/f3/Ognisko_ubt_0126.jpeg", "рэчыва"),
            Word("чалавек", "ча-ла-век", "https://upload.wikimedia.org/wikipedia/commons/2/22/Da_Vinci_Vitruve_Luc_Viatour.jpg", ""),
            Word("рамонак", "ра-мо-нак", "https://upload.wikimedia.org/wikipedia/commons/0/07/Matricaria_recutita_2008_07_06.JPG", "кветкі"),
            Word("парасон", "па-ра-сон", "https://upload.wikimedia.org/wikipedia/commons/d/d4/Gustave_Caillebotte_-_Jour_de_pluie_%C3%A0_Paris.jpg", ""),
            Word("ракета", "ра-ке-та", "https://upload.wikimedia.org/wikipedia/commons/8/8b/Proton-K-Zarya.jpg", ""),
            Word("капялюш", "ка-пя-люш", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5d/Pierre_Auguste_Renoir_-_Woman_in_a_Flowered_Hat_-_Google_Art_Project.jpg/800px-Pierre_Auguste_Renoir_-_Woman_in_a_Flowered_Hat_-_Google_Art_Project.jpg", ""),

            Word("ноль", "ноль", "https://www.vectorportal.com/img_novi/flat-calculator.jpg", "лічбы"),
            Word("адзін", "а-дзін", "https://www.vectorportal.com/img_novi/pete1_6859.jpg", "лічбы"),
            Word("два", "два", "https://www.vectorportal.com/img_novi/hdpe2_6851.jpg", "лічбы"),
            Word("тры", "тры", "https://www.vectorportal.com/img_novi/pvc3_6862.jpg", "лічбы"),
            Word("чатыры", "ча-ты-ры", "https://www.vectorportal.com/img_novi/ldpe4_6852.jpg", "лічбы"),
            Word("пяць", "пяць", "https://www.vectorportal.com/img_novi/recyclable5a_6863.jpg", "лічбы"),
            Word("шэсць", "шэсць", "https://www.vectorportal.com/img_novi/ps6_6861.jpg", "лічбы"),
            Word("сем", "сем", "https://www.vectorportal.com/StockVectors/Symbols-and-Signs/VECTOR-SYMBOL-FOR-OTHER-7/6853.aspx", "лічбы"),
            Word("восем", "во-сем", "https://www.vectorportal.com/img_novi/snooker-ball-vector_8432.jpg", "лічбы"), Word("дзевяць", "дзе-вяць", "https://image.freepik.com/free-photo/hand-holding-number-nine-sign_53876-47259.jpg", "лічбы"), Word("дзесяць", "дзе-сяць", "https://www.vectorportal.com/img_novi/step-descent.jpg", "лічбы")
    )
}