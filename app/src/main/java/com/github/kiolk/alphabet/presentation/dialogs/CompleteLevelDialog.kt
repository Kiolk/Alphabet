package com.github.kiolk.alphabet.presentation.dialogs

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import com.bumptech.glide.Glide
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.data.models.level.Level

class CompleteLevelDialog: BaseInfoDialog {
    constructor(args: Bundle): super(args)

    @BindView(R.id.iv_complete_level_image)
    lateinit var ivLevelImage: ImageView

    @BindView(R.id.tv_complete_level_name)
    lateinit var tvLevelName: TextView

    override fun getLayout(): Int = R.layout.controller_coplete_level

    override fun onViewBound(view: View) {
        super.onViewBound(view)

        dialog?.let { isCancelable = true }

        val level: Level = args.getParcelable(BUNDLE_LEVEL) as Level

        tvLevelName.text = "Цяпер ты ${level.title}"
        ivLevelImage.setImageResource(level.image)

        Glide.with(ivLevelImage.context)
                .load(level.image)
                .into(ivLevelImage)

    }

    companion object {
        private const val BUNDLE_LEVEL = "BUNDLE_LEVEL"

        fun getInstance(level: Level): CompleteLevelDialog{
            val bundle = Bundle()
            bundle.putParcelable(BUNDLE_LEVEL, level)
            return CompleteLevelDialog(bundle)
        }
    }
}