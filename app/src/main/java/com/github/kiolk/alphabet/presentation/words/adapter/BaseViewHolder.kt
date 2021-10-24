package com.github.kiolk.alphabet.presentation.words.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import butterknife.ButterKnife

abstract class BaseViewHolder<T>(itemView : View) : RecyclerView.ViewHolder(itemView) {

    init {
        ButterKnife.bind(this, itemView)
    }

    abstract fun onBindViewHolder(data : T)

    fun getContext() = itemView.context
}