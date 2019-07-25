package com.github.kiolk.alphabet.presentation.dialogs

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.OnClick
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.data.models.topic.Topic

class CompleteTopicDialog: BaseInfoDialog {

    @BindView(R.id.iv_compete_topic_image)
    lateinit var ivTopicImage: ImageView

    @BindView(R.id.tv_complete_topic_description)
    lateinit var tvDescription: TextView

    lateinit var listener: () -> Unit

    constructor(args: Bundle, acceptListener: () -> Unit) : super(args){
        this.listener = acceptListener
    }

    constructor(args: Bundle) : super(args)

    override fun getLayout(): Int = R.layout.controller_complete_topic

    override fun onViewBound(view: View) {
        super.onViewBound(view)

        dialog?.let { isCancelable }
    }

    @OnClick(R.id.btn_complete_topic)
    fun onOkPress(){
        listener.invoke()
    }

    companion object {
        private const val BUNDLE_TOPIC = "BUNDLE_TOPIC"

        fun getInstance(topic: Topic, acceptListener: () -> Unit): CompleteTopicDialog{
            val bundle = Bundle()
            bundle.putParcelable(BUNDLE_TOPIC, topic)
            return CompleteTopicDialog(bundle, acceptListener)
        }
    }

}