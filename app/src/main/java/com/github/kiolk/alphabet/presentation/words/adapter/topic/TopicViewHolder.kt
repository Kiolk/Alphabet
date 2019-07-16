package com.github.kiolk.alphabet.presentation.words.adapter.topic

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import butterknife.BindView
import com.bumptech.glide.Glide
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.data.models.topic.Topic
import com.github.kiolk.alphabet.presentation.words.adapter.BaseViewHolder

class TopicViewHolder(itemView: View, val listener: (Topic) -> Unit): BaseViewHolder<Topic>(itemView) {

    @BindView(R.id.iv_topic_picture)
    lateinit var ivTopicPicture: ImageView

    @BindView(R.id.tv_topic_title)
    lateinit var tvTopicTitle: TextView

    @BindView(R.id.pb_topic_progress)
    lateinit var pbTopicProgress: ProgressBar

    override fun onBindViewHolder(data: Topic) {
          Glide.with(getContext())
                  .load(data.picture)
                  .into(ivTopicPicture)

        val title = "${data.title} ${data.read}/${data.total}"
        tvTopicTitle.text = title

        pbTopicProgress.max = data.total
        pbTopicProgress.progress = data.read

        ivTopicPicture.setOnClickListener {
            listener.invoke(data)
        }

        itemView.setOnClickListener{
        }
    }
}