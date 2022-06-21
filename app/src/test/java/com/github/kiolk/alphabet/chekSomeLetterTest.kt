package com.github.kiolk.alphabet

import com.github.kiolk.common.data.model.word.GameSchema
import com.github.kiolk.common.data.model.word.GameSettings
import com.github.kiolk.common.data.model.word.Word
import junit.framework.Assert.assertEquals
import org.junit.Test


class chekSomeLetterTest {

    @Test
    fun checkSomeLetter() {

        val word = Word("дзень", "", "", "", 0)
        val z = GameSettings("", "", "", "",
                "", 4, true, true, GameSchema(3, "з", 1, 1), 0, 0)
        val d = GameSettings("", "", "", "",
                "", 4, true, true, GameSchema(3, "д", 1, 1), 0, 0)
        val dz = GameSettings("", "", "", "",
                "", 4, true, true, GameSchema(3, "дз", 1, 1), 0, 0)

        assertEquals(false, Word.checkDoubleLetter(word, z))
        assertEquals(false, Word.checkDoubleLetter(word, d))
        assertEquals(true, Word.checkDoubleLetter(word, dz))
    }

    @Test
    fun checkDzhLetter(){
        val wordRain = Word("дождж", "", "", "", 0)
        val z = GameSettings("", "", "", "",
                "", 4, true, true, GameSchema(3, "ж", 1, 1), 0, 0)
        val d = GameSettings("", "", "", "",
                "", 4, true, true, GameSchema(3, "д", 1, 1), 0, 0)
        val dz = GameSettings("", "", "", "",
                "", 4, true, true, GameSchema(3, "дж", 1, 1), 0, 0)


        assertEquals(false, Word.checkDoubleLetter(wordRain, z))
        assertEquals(false, Word.checkDoubleLetter(wordRain, d))
        assertEquals(true, Word.checkDoubleLetter(wordRain, dz))
    }
}
