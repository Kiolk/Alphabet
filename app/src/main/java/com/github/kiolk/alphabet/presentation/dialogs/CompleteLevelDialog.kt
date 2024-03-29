package com.github.kiolk.alphabet.presentation.dialogs

import android.os.Bundle
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.OnClick
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.data.models.level.LevelType
import com.github.kiolk.alphabet.presentation.views.LevelLebel

class CompleteLevelDialog: BaseInfoDialog {
    constructor(args: Bundle): super(args)

    @BindView(R.id.ll_complete_level_label)
    lateinit var llLable: LevelLebel

    @BindView(R.id.tv_complete_level_name)
    lateinit var tvLevelName: TextView

    override fun getLayout(): Int = R.layout.controller_coplete_level

    override fun onViewBound(view: View) {
        super.onViewBound(view)

        dialog?.setCancelable(true)

        val level: LevelType = (args.getParcelable(BUNDLE_LEVEL) as? LevelType) ?: return

        tvLevelName.text = "${level.name}"
        llLable.setLevelImage(level.imageId)
        llLable.setLevel(level.needStars.toString())
    }

    @OnClick(R.id.btn_complete_topic)
    fun onOkPress(){
        router.popCurrentController()
    }

    companion object {
        private const val BUNDLE_LEVEL = "BUNDLE_LEVEL"

        fun getInstance(level: LevelType): CompleteLevelDialog{
            val bundle = Bundle()
            bundle.putParcelable(BUNDLE_LEVEL, level)
            return CompleteLevelDialog(bundle)
        }
    }
}