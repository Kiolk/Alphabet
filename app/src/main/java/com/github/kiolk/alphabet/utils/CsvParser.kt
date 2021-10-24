package com.github.kiolk.alphabet.utils

import com.github.kiolk.alphabet.data.models.word.Word
import java.io.BufferedReader
import java.io.File
import java.util.*

class CsvParser {

    companion object {
        fun parserToWords(path: String): List<Word> {
            var reader: BufferedReader? = null
            var line: String? = ""
            val words: MutableList<Word> = mutableListOf()
            try {

                val scanner = Scanner(File(path))
                scanner.useDelimiter(",")
                while(scanner.hasNextLine()){
                    line = scanner.nextLine()
                    val values = line.split(",")
                    val word = Word(values[0], values[1], values[2], values[3])
                    words.add(word)
                }
            } catch (e: Exception) {
            } finally {
                reader?.close()
            }
            return words
        }
    }
}