package com.github.kiolk.alphabet.presentation.words.adapter.alphabet

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import butterknife.BindView
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.data.models.letter.Letter
import com.github.kiolk.alphabet.presentation.words.adapter.BaseViewHolder

class AlphabetViewHolder(itemView: View, val listener: ((Letter) -> Unit)) : BaseViewHolder<Letter>(itemView) {

    @BindView(R.id.tw_alphabet_letter)
    lateinit var tvLetter: TextView

    @BindView(R.id.cl_letter_item_container)
    lateinit var clContainer: ConstraintLayout

    override fun onBindViewHolder(data: Letter) {
        tvLetter.text = data.letterValue
        tvLetter.setOnClickListener {
            listener.invoke(data)
        }

        clContainer.isEnabled = true
        clContainer.isClickable = true
        clContainer.isFocusable = true

        when (data.completedLevel) {
            -1f -> {
                clContainer.isEnabled = false
                clContainer.isClickable = false
                clContainer.isFocusable = false
                clContainer.background = getContext().resources.getDrawable(R.drawable.bg_letter_round_gray)
                tvLetter.setOnClickListener {  }
            }
            0f -> clContainer.background = getContext().resources.getDrawable(R.drawable.bg_letter_round_red)
            1f -> clContainer.background = getContext().resources.getDrawable(R.drawable.bg_letter_round_green)
            else -> clContainer.background = getContext().resources.getDrawable(R.drawable.bg_letter_round_yellow)
        }
    }
}