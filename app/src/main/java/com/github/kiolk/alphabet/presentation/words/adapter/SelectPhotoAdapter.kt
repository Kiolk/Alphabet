package com.github.kiolk.alphabet.presentation.words.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.data.models.word.Word

class SelectPhotoAdapter

    constructor(private val context: Context, private var list : List<Word>, private val listener :((word : Word) -> Unit)): RecyclerView.Adapter<SelectPhotoViewHolder>() {

    private var userAnswerPosition : Int = -1
    private var correctPosition : Int = - 1
    var isEnableSelected : Boolean = false

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SelectPhotoViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_photo_item, null, false)
        return SelectPhotoViewHolder(view){word ->
            onPhotoPress(word)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(viewHolder : SelectPhotoViewHolder, position: Int) {
        viewHolder.onBindViewHolder(list[position])
    }

    fun setItems(list: List<Word>) {
        this.list = list
        userAnswerPosition = -1
        correctPosition = -1
        notifyDataSetChanged()
        isEnableSelected = false
    }

    override fun getItemViewType(position: Int): Int {
        return when(position){
            correctPosition -> CORRECT
            userAnswerPosition -> WRONG_ANSWER
            else -> NORMAL_TYPE
        }
    }

    fun setCorrectAnswer(userAnswer : Word, correct : Word){
        userAnswerPosition = list.indexOf(userAnswer)
        correctPosition = list.indexOf(correct)
        notifyDataSetChanged()
    }

    private fun onPhotoPress(word : Word){
        if(!isEnableSelected){
            isEnableSelected = true
            listener(word)
        }
    }

    companion object {
        const val NORMAL_TYPE = 0
        const val WRONG_ANSWER = 1
        const val CORRECT = 2
    }
}