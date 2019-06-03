package com.github.kiolk.alphabet.presentation.words.adapter.alphabet

import android.view.View
import android.widget.TextView
import butterknife.BindView
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.data.models.letter.Letter
import com.github.kiolk.alphabet.presentation.words.adapter.BaseViewHolder

class AlphabetViewHolder(itemView : View, val listener : ((Letter) -> Unit)) : BaseViewHolder<Letter>(itemView) {

    @BindView(R.id.tw_alphabet_letter)
    lateinit var tvLetter : TextView

    override fun onBindViewHolder(data: Letter) {
        tvLetter.text = data.letterValue
        tvLetter.setOnClickListener{
            listener.invoke(data)
        }
    }
}