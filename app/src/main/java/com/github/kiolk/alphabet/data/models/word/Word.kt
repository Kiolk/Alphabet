package com.github.kiolk.alphabet.data.models.word

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.github.kiolk.alphabet.data.database.converters.ListConverter
import com.github.kiolk.alphabet.data.models.game.GameSettings
import kotlinx.parcelize.Parcelize
import java.util.regex.Pattern

@Entity(tableName = "Word")
@TypeConverters(ListConverter::class)
@Parcelize
data class Word(
        @PrimaryKey
        @ColumnInfo(name = WORD_MEAN)
        val value: String,
        @ColumnInfo(name = "word_by_syllables")
        val syllables: String,
        @ColumnInfo(name = WORD_IMAGE)
        val image: String,
        @ColumnInfo(name = TAGS)
        val tags: String,
        @ColumnInfo(name = "read")
        var read: Int = 0,
        @ColumnInfo(name = "author")
        val author: String = "") : Parcelable {
    companion object {
        const val WORD_MEAN: String = "word_mean"
        const val TAGS: String = "tags"
        const val WORD_IMAGE: String = "word_image"

        fun checkDoubleLetter(word: Word, setting: GameSettings): Boolean {
            val letterValue = setting.gameSchema.letterValue

            if (letterValue == "з" || letterValue == "ж") {
                val patter = Pattern.compile("([^д][$letterValue])|([$letterValue])")

                return patter.matcher(word.value).matches()
            } else if (letterValue == "д") {
                val patter = Pattern.compile("([д][^з])|([д][^ж])")

                return patter.matcher(word.value).matches()
            }

            return true
        }
    }
}
