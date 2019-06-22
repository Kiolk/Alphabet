package com.github.kiolk.alphabet.presentation.adapters

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.data.models.game.GameSettings
import com.github.kiolk.alphabet.presentation.views.GameIndicator
import com.github.kiolk.alphabet.presentation.words.adapter.BaseViewHolder

class TopicAdapter(val topics: List<GameSettings>, val context: Context, val listener: OnItemClickListener) : RecyclerView.Adapter<TopicAdapter.TopicViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TopicAdapter.TopicViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_topic, p0, false)
        return TopicViewHolder(view, listener)
    }

    override fun getItemCount(): Int {
        return topics.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (topics.get(position).isAvailable) {
            true -> AVAILABLE
            false -> UNAVAILABLE
        }
    }

    override fun onBindViewHolder(p0: TopicViewHolder, p1: Int) {
        p0.onBindViewHolder(topics.get(p1))
    }

    class TopicViewHolder(itemView: View, val listener: OnItemClickListener) :BaseViewHolder<GameSettings>(itemView) {

        @BindView(R.id.iv_topic_blur_layout)
        lateinit var ivBlur: View

        @BindView(R.id.gi_topic_item)
        lateinit var giTopic: GameIndicator

        @BindView(R.id.cv_topic)
        lateinit var cvCard: CardView

        override fun onBindViewHolder(data: GameSettings) {
            giTopic.setGameSchema(data.gameSchema)

            if (itemViewType == AVAILABLE) {
                itemView.setOnClickListener { listener.onItemClick(data) }
                ivBlur.visibility = View.GONE
                cvCard.isEnabled = true
                cvCard.isClickable = true
            } else {
                ivBlur.visibility = View.VISIBLE
                ivBlur.background = getContext().resources.getDrawable(R.drawable.bg_gray_lock)
                cvCard.isClickable = false
                cvCard.isEnabled = false
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(settings: GameSettings)
    }

    companion object {
        const val AVAILABLE: Int = 0
        const val UNAVAILABLE: Int = 1
    }
}

data class Topic(val image: Int, val title: String, val query: String = "", val isAvailable: Boolean)
