package com.github.kiolk.alphabet.presentation.words.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.ButterKnife

abstract class BaseViewHolder<T>(itemView : View) : RecyclerView.ViewHolder(itemView) {

    init {
        ButterKnife.bind(this, itemView)
    }

    abstract fun onBindViewHolder(data : T)

    fun getContext() = itemView.context
}