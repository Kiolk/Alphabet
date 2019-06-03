package com.github.kiolk.alphabet.presentation.common

import android.app.Activity
import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.Gravity
import android.widget.TextView
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.utils.toPx
import com.google.android.flexbox.AlignContent
import com.google.android.flexbox.AlignContent.FLEX_START
import com.google.android.flexbox.AlignItems.CENTER
import com.google.android.flexbox.FlexWrap.WRAP
import com.google.android.flexbox.FlexboxLayout

class CharactersLayout : FlexboxLayout {

    var widthLayout: Int = 0

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
            context,
            attrs,
            defStyleAttr
    )

    init {
        val metrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(metrics)
        widthLayout = metrics.widthPixels

        alignContent = AlignContent.STRETCH
        alignItems = CENTER
        flexWrap = WRAP
        justifyContent = FLEX_START
    }

    fun setWord(word: String) {
        this.removeAllViews()
        for (char in word) {
            val view = TextView(context).apply {
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 60.toFloat())
                text = char.toString()
                gravity = Gravity.CENTER
                if (char != ' ') {
                    background = ContextCompat.getDrawable(context, R.drawable.char_drawable)
                }
                if (char in charG) {
                    setTextColor(ContextCompat.getColorStateList(context, R.color.glasnye))
                } else if (char != '-') {
                    setTextColor(ContextCompat.getColorStateList(context, R.color.soglasnye))
                }
                val lpRight = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)

                lpRight.rightMargin = 1.toPx
                lpRight.width = (widthLayout / (widthLayout / 150))
                lpRight.setMargins(4.toPx, 4.toPx, 4.toPx, 4.toPx)
                setPadding(8.toPx, 1.toPx, 8.toPx, 1.toPx)

                layoutParams = lpRight
            }
            addView(view)
        }
    }

    companion object {
        val charG: Array<Char> = arrayOf('а', 'о', 'э', 'у', 'ю', 'ы', 'ё', 'е', 'і', 'я')
    }
}