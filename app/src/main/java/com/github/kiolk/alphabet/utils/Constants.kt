package com.github.kiolk.alphabet.utils

import com.github.kiolk.alphabet.data.models.game.GameSettingBuilder
import com.github.kiolk.alphabet.data.models.game.GameSettings
import com.github.kiolk.alphabet.data.models.letter.Letter
import com.github.kiolk.alphabet.data.models.word.Word

object Data{
    val alphabet = listOf(Letter("а", "а", "d"),
            Letter("б", "б", "d"),
            Letter("в", "в", "d"),
            Letter("г", "г", "d"),
            Letter("д", "д", "d"),
            Letter("дж", "дж", "d"),
            Letter("дз", "дз", "d"),
            Letter("е", "е", "d"),
            Letter("ё", "ё", "d"),
            Letter("ж", "ж", "d"),
            Letter("з", "з", "d"),
            Letter("і", "і", "d"),
            Letter("й", "й", "d"),
            Letter("к", "к", "d"),
            Letter("л", "л", "d"),
            Letter("м", "м", "d"),
            Letter("н", "н", "d"),
            Letter("о", "о", "d"),
            Letter("п", "п", "d"),
            Letter("р", "р", "d"),
            Letter("с", "с", "d"),
            Letter("т", "т", "d"),
            Letter("у", "у", "d"),
            Letter("ў", "ў", "d"),
            Letter("ф", "ф", "d"),
            Letter("х", "х", "d"),
            Letter("ц", "ц", "d"),
            Letter("ч", "ч", "d"),
            Letter("ш", "ш", "d"),
            Letter("ы", "ы", "d"),
            Letter("ь", "ь", "d"),
            Letter("э", "э", "d"),
            Letter("ю", "ю", "d"),
            Letter("я", "я", "d")
    )

    val gameSettingsPatterns: List<GameSettingBuilder> = listOf<GameSettingBuilder>(
            GameSettingBuilder().setTitle("Тры").setLettersInWord(3).setPosition(GameSettingBuilder.Position.ANY).setGameLevel(1),
            GameSettingBuilder().setTitle("Чатыры").setLettersInWord(4).setPosition(GameSettingBuilder.Position.ANY).setGameLevel(2),
            GameSettingBuilder().setTitle("Пяць").setLettersInWord(5).setPosition(GameSettingBuilder.Position.ANY).setGameLevel(3),
            GameSettingBuilder().setTitle("Шэсць").setLettersInWord(6).setPosition(GameSettingBuilder.Position.ANY).setGameLevel(4),
            GameSettingBuilder().setTitle("Літара").setPosition(GameSettingBuilder.Position.ANY).setGameLevel(5),
            GameSettingBuilder().setTitle("Пачатак").setPosition(GameSettingBuilder.Position.START).setGameLevel(6)
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