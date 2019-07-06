package com.github.kiolk.alphabet.presentation.words.adapter.alphabet

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.CardView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.data.models.letter.Letter
import com.github.kiolk.alphabet.presentation.words.adapter.BaseViewHolder

class AlphabetViewHolder(itemView : View, val listener : ((Letter) -> Unit)) : BaseViewHolder<Letter>(itemView) {

    @BindView(R.id.tw_alphabet_letter)
    lateinit var tvLetter : TextView

    @BindView(R.id.cv_letter_item)
    lateinit var cvItemCard: CardView

    @BindView(R.id.cl_letter_item_container)
    lateinit var clContainer: ConstraintLayout

    override fun onBindViewHolder(data: Letter) {
        cvItemCard.isEnabled = true
        cvItemCard.isClickable = true

        tvLetter.text = data.letterValue
        tvLetter.setOnClickListener{
            listener.invoke(data)
        }

        when(data.completedLevel){
            -1f -> clContainer.setBackgroundColor(getContext().resources.getColor(R.color.general_dark_gray))
            0f -> clContainer.setBackgroundColor(getContext().resources.getColor(R.color.general_red))
            1f -> clContainer.setBackgroundColor(getContext().resources.getColor(R.color.general_green))
            else -> clContainer.setBackgroundColor(getContext().resources.getColor(R.color.general_orange))
        }
    }
}