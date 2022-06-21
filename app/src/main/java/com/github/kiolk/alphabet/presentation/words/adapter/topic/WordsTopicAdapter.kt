package com.github.kiolk.alphabet.presentation.words.adapter.topic

import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.presentation.base.adapter.BaseRecyclerViewAdapter
import com.github.kiolk.common.data.model.word.Topic

class WordsTopicAdapter(val listener: ((Topic) -> Unit)): BaseRecyclerViewAdapter<Topic, TopicViewHolder>() {

    override fun onCreateViewHolder(container: ViewGroup, p1: Int): TopicViewHolder {
        val view = LayoutInflater.from(container.context).inflate(R.layout.layout_words_set, container, false)
        return TopicViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        holder.onBindViewHolder(resources[position])
    }
}