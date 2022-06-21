package com.github.kiolk.alphabet.presentation.words.adapter.topic

import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import com.bumptech.glide.Glide
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.presentation.views.StyledProgressBar
import com.github.kiolk.alphabet.presentation.words.adapter.BaseViewHolder
import com.github.kiolk.alphabet.utils.toPx
import com.github.kiolk.common.data.model.word.Topic

class TopicViewHolder(itemView: View, val listener: (Topic) -> Unit): BaseViewHolder<Topic>(itemView) {

    @BindView(R.id.iv_topic_picture)
    lateinit var ivTopicPicture: ImageView

    @BindView(R.id.tv_topic_title)
    lateinit var tvTopicTitle: TextView

    @BindView(R.id.sb_topic_progress_bar)
    lateinit var pbProgressTopic: StyledProgressBar

    override fun onBindViewHolder(data: Topic) {
          Glide.with(getContext())
                  .load(data.picture)
                  .into(ivTopicPicture)

        val title = "${data.title} ${data.read}/${data.total}"
        tvTopicTitle.inputType = InputType.TYPE_TEXT_FLAG_CAP_WORDS
        tvTopicTitle.text = title
        Log.d("MyLogs", data.toString())
        pbProgressTopic.setHeight(30.toPx)
        pbProgressTopic.setBaseBackground(R.drawable.bg_progress_square_background)
        pbProgressTopic.setBaseForeground(R.drawable.bg_progress_round_square_foreground)
        pbProgressTopic.setCompleteForeground(R.drawable.bg_progress_square_foreground)
        pbProgressTopic.setInitialValue("")
        pbProgressTopic.setFinalValue("")
        pbProgressTopic.setProgress(((data.read / data.total.toFloat()) * 100).toInt())

        itemView.setOnClickListener{
            listener.invoke(data)
        }
    }
}