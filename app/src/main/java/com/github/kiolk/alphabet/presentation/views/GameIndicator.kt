package com.github.kiolk.alphabet.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.data.models.game.GameSettingBuilder
import com.github.kiolk.common.data.model.word.GameSchema

class GameIndicator : LinearLayout {

    private lateinit var vFirstLeft: View
    private lateinit var vSecondLeft: View
    private lateinit var vFirstRight: View
    private lateinit var vSecondRight: View
    private lateinit var tvLetter: TextView
    private lateinit var tvCounter: TextView
    private lateinit var tvWordLenght: TextView
    private lateinit var ivCorrectIndicator: ImageView
    private lateinit var ivFirstStar: ImageView
    private lateinit var ivSecondStar: ImageView
    private lateinit var ivThirdStar: ImageView


    constructor(context: Context?) : super(context) {
        init(null)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
            context,
            attrs,
            defStyleAttr
    ) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        LayoutInflater.from(context).inflate(R.layout.layout_game_indicator, this)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        vFirstLeft = findViewById(R.id.v_game_indicator_first_left)
        vSecondLeft = findViewById(R.id.v_game_indicator_second_left)
        vFirstRight = findViewById(R.id.v_game_indicator_first_right)
        vSecondRight = findViewById(R.id.v_game_indicator_second_right)
        tvLetter = findViewById(R.id.tv_game_indicator_latter)
        tvCounter = findViewById(R.id.tv_game_indicator_counter)
        tvWordLenght = findViewById(R.id.tv_game_indicator_word_length)
        ivCorrectIndicator = findViewById(R.id.iv_game_indicator_correct)
        ivFirstStar = findViewById(R.id.iv_game_indicator_first_star)
        ivSecondStar = findViewById(R.id.iv_game_indicator_second_star)
        ivThirdStar = findViewById(R.id.iv_game_indicator_third_star)

    }

    fun setLtter(letter: String) {
        tvLetter.text = letter
        vFirstLeft.visibility = View.GONE
        vSecondLeft.visibility = View.GONE
        vFirstRight.visibility = View.GONE
        vSecondRight.visibility = View.GONE
        tvCounter.visibility = View.GONE
        tvWordLenght.visibility = View.GONE
    }

    fun setInMiddle() {
        vFirstLeft.visibility = View.VISIBLE
        vFirstRight.visibility = View.VISIBLE
    }

    fun setInStart() {
        vFirstRight.visibility = View.VISIBLE
        vSecondRight.visibility = View.VISIBLE
    }

    fun setInEnd() {
        vFirstLeft.visibility = View.VISIBLE
        vSecondLeft.visibility = View.VISIBLE
    }

    fun setIndicator(count: String) {
        tvCounter.visibility = View.VISIBLE
        tvCounter.text = count
    }

    fun setWordLength(length: String) {
        tvWordLenght.visibility = View.VISIBLE
        tvWordLenght.text = length
    }

    fun setCorrect(isCorrect: Boolean) {
        ivCorrectIndicator.visibility = if (isCorrect) View.GONE else View.GONE
    }

    fun setGameSchema(schema: GameSchema, stars: Int) {

        setLtter(schema.letterValue)

        when (GameSettingBuilder.Position.getPosition(schema.position)) {
            GameSettingBuilder.Position.START -> setInStart()
            GameSettingBuilder.Position.END -> setInEnd()
            GameSettingBuilder.Position.INSIDE -> setInMiddle()
        }
        if (schema.length > 0) {
            setWordLength(schema.length.toString())
        }

        if (schema.numberOfLetters > 1) {
            setIndicator(schema.numberOfLetters.toString())
        }

        ivFirstStar.visibility = View.INVISIBLE
        ivSecondStar.visibility = View.INVISIBLE
        ivThirdStar.visibility = View.INVISIBLE


        when (stars) {
            1 -> {
                ivFirstStar.visibility = View.VISIBLE
            }
            2 -> {
                ivFirstStar.visibility = View.VISIBLE
                ivSecondStar.visibility = View.VISIBLE
            }
            3 -> {
                ivFirstStar.visibility = View.VISIBLE
                ivSecondStar.visibility = View.VISIBLE
                ivThirdStar.visibility = View.VISIBLE

            }
        }
    }
}