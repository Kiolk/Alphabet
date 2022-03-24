package com.github.kiolk.alphabet.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.data.models.game.GameSettings
import com.github.kiolk.alphabet.presentation.views.GameIndicator
import com.github.kiolk.alphabet.presentation.words.adapter.BaseViewHolder

class TopicAdapter(val topics: List<GameSettings>, val listener: OnItemClickListener) : RecyclerView.Adapter<TopicAdapter.TopicViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TopicAdapter.TopicViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.layout_topic, p0, false)
        return TopicViewHolder(view, listener)
    }

    override fun getItemCount(): Int {
        return topics.size
    }

    override fun getItemViewType(position: Int): Int {
        if(!topics.get(position).isAvailable){
            return UNAVAILABLE
        }else if(topics.get(position).isCompleted){
            return COMPLETED
        }else {
            return AVAILABLE
        }
    }

    override fun onBindViewHolder(p0: TopicViewHolder, p1: Int) {
        p0.onBindViewHolder(topics.get(p1))
    }

    class TopicViewHolder(itemView: View, val listener: OnItemClickListener) :BaseViewHolder<GameSettings>(itemView) {

        @BindView(R.id.gi_topic_item)
        lateinit var giTopic: GameIndicator

        @BindView(R.id.cl_topic_item_container)
        lateinit var clContainer: ConstraintLayout

        override fun onBindViewHolder(data: GameSettings) {
            giTopic.setGameSchema(data.gameSchema, data.stars )

            if (itemViewType == AVAILABLE) {
                itemView.setOnClickListener { listener.onItemClick(data) }
                clContainer.background = getContext().resources.getDrawable(R.drawable.bg_topic_round_blue)
                giTopic.setCorrect(false)
                clContainer.isEnabled = true
                clContainer.isClickable = true
            } else if(itemViewType == UNAVAILABLE) {
                giTopic.setCorrect(false)
                clContainer.background = getContext().resources.getDrawable(R.drawable.bg_topic_round_light_blue)
                clContainer.isClickable = false
                clContainer.isEnabled = false
            }else if(itemViewType == COMPLETED){
                giTopic.setCorrect(true)
                itemView.setOnClickListener { listener.onItemClick(data) }
                clContainer.background = getContext().resources.getDrawable(R.drawable.bg_topic_round_blue)
                clContainer.isEnabled = true
                clContainer.isClickable = true
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(settings: GameSettings)
    }

    companion object {
        const val AVAILABLE: Int = 0
        const val UNAVAILABLE: Int = 1
        const val COMPLETED: Int = 2
    }
}

data class Topic(val image: Int, val title: String, val query: String = "", val isAvailable: Boolean)
