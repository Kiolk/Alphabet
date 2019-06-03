package com.github.kiolk.alphabet.presentation.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import com.bumptech.glide.Glide
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.data.models.game.GameSettings
import com.github.kiolk.alphabet.presentation.words.adapter.BaseViewHolder

class TopicAdapter(val topics: List<GameSettings>, val context: Context, val listener: OnItemClickListener) : RecyclerView.Adapter<TopicAdapter.TopicViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TopicAdapter.TopicViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_book, p0, false)
        return TopicViewHolder(view, listener)
    }

    override fun getItemCount(): Int {
        return topics.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (topics.get(position) != null) {
            true -> AVAILABLE
            false -> UNAVAILABLE
        }
    }

    override fun onBindViewHolder(p0: TopicViewHolder, p1: Int) {
        p0.onBindViewHolder(topics.get(p1))
    }

    class TopicViewHolder(itemView: View, val listener: OnItemClickListener) :BaseViewHolder<GameSettings>(itemView) {
//
//        init {
//            ButterKnife.bind(this, itemView)
//        }

        @BindView(R.id.im_books_list_book_cover)
        lateinit var imTopic: ImageView

        @BindView(R.id.tw_book_list_book_title)
        lateinit var tvTitle: TextView

        @BindView(R.id.iv_topics_unlock_icon)
        lateinit var ivUnlockIcon: ImageView

        @BindView(R.id.fl_topics_lock_screen)
        lateinit var flLockScreen: FrameLayout

        override fun onBindViewHolder(data: GameSettings) {
                      Glide.with(itemView)
                    .load(data.pictureUrl)
                    .into(imTopic)

            tvTitle.text = data.title

            if (itemViewType == AVAILABLE) {
                ivUnlockIcon.visibility = View.VISIBLE
                itemView.setOnClickListener(object : View.OnClickListener {
                    override fun onClick(v: View?) {
                        listener.onItemClick(data)
                    }
                })
            } else {
                flLockScreen.visibility = View.VISIBLE
            }

        }
    }

    fun updateTopic(updateTopic: Topic) {
        var position: Int = 0
        topics.forEachIndexed { index, topic ->
            if (topic.title == updateTopic.title) {
//                topic.isAvailable == updateTopic.isAvailable
                position = index
            }
        }
        notifyItemChanged(position)
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
