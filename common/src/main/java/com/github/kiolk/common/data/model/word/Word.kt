package com.github.kiolk.common.data.model.word

import android.os.Parcelable
import com.github.kiolk.common.data.model.image.Image
import kotlinx.parcelize.Parcelize
import java.util.regex.Pattern

@Parcelize
data class Word(
    val value: String,
    val syllables: String,
    val image: String,
    val tags: String,
    var read: Int = 0,
    val author: String = "",
    val id: String = "",
    val images: List<Image> = emptyList()
) : Parcelable {
    companion object {
        const val ID = "id"
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
