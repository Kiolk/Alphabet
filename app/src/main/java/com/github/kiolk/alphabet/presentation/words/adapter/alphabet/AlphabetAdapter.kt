package com.github.kiolk.alphabet.presentation.words.adapter.alphabet

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.data.models.letter.Letter

class AlphabetAdapter(private val context: Context, private var letters : List<Letter>, private val listener : (Letter) -> Unit) : RecyclerView.Adapter<AlphabetViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): AlphabetViewHolder {
       val view = LayoutInflater.from(context).inflate(R.layout.layout_letter_item, p0, false)
        return AlphabetViewHolder(view){letter ->
            listener.invoke(letter)
        }
    }

    override fun getItemCount(): Int = letters.size

    override fun onBindViewHolder(viewHolder: AlphabetViewHolder, position: Int) {
        viewHolder.onBindViewHolder(letters.get(position))
    }

    fun setItems(list : List<Letter>){
        letters = list
        notifyDataSetChanged()
    }
}