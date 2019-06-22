package com.github.kiolk.alphabet.data.models.game

import java.lang.StringBuilder

class GameSettingBuilder {

    private var letter: String = ""
    private var numberOfLetters: Int = 1
    private var lettersInWord: Int = 1
    private var position: Position = Position.START
    private var title: String = ""

    fun setTitle(title: String): GameSettingBuilder{
        this.title = title
        return this
    }

    fun setLetter(letter: String): GameSettingBuilder{
        this.letter = letter
        return this
    }

    fun setPosition(position: Position): GameSettingBuilder{
        this.position = position
        return this
    }

    fun setNumberOfLetters(number: Int): GameSettingBuilder{
        this.numberOfLetters = number
        return this
    }

    fun setLettersInWord(letters: Int): GameSettingBuilder{
        this.lettersInWord = letters
        return this
    }

    fun build(): GameSettings{
        return GameSettings("$title $letter", "", formWordLenght(), formNumberOfLeters(),
                formPostion(), 4, false, false,
                GameSchema(numberOfLetters, letter, position.value, lettersInWord))
    }

    private fun formWordLenght(): String{
        val strinBuilder: StringBuilder = StringBuilder()
        for(number in 0 until lettersInWord){
            strinBuilder.append("_")
        }
        return strinBuilder.toString()
    }

    private fun formPostion(): String{
        return when(position){
            Position.START -> "$letter%"
            Position.INSIDE -> "_%$letter%_"
            Position.END -> "%$letter"
            Position.ANY -> "%$letter%"
        }
    }

    private fun formNumberOfLeters():String{
        val stringBuilder: StringBuilder = StringBuilder()
        for(number in 0 until numberOfLetters){
            stringBuilder.append("%$letter%")
        }
        return stringBuilder.toString()
    }

    enum class Position(val value: Int){
        START(0), END(1), INSIDE(2), ANY(3);
        companion object {
            fun getPosition(value: Int): Position{
                return Position.values().find { position -> position.value == value } ?: START
            }
        }
    }
}