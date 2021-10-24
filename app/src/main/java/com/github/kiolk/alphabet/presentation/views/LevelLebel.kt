package com.github.kiolk.alphabet.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.github.kiolk.alphabet.R

class LevelLebel : ConstraintLayout {

    private lateinit var tvLevelIndicator: TextView
    private lateinit var ivLevelImage: ImageView

    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        LayoutInflater.from(context).inflate(R.layout.layout_level_label, this)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        tvLevelIndicator = findViewById(R.id.tv_level_stars_indicator)
        ivLevelImage= findViewById(R.id.iv_level_image)
    }

    fun setLevel(level: String){
        tvLevelIndicator.text = level
    }

    fun setLevelImage(resId: Int){
        ivLevelImage.setImageResource(resId)
    }
}