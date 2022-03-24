package com.github.kiolk.alphabet.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewTreeObserver
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.github.kiolk.alphabet.R

class StyledProgressBar : ConstraintLayout {

    private lateinit var vBackground: View
    private lateinit var vForeground: View
    private lateinit var tvFinalValue: TextView
    private lateinit var tvInitialValue: TextView
    private lateinit var tvTarget: TextView
    private lateinit var clContainer: ConstraintLayout
    private var currentWidth: Int = 869
    private var currentMinWidth: Int = 100
    private lateinit var listenerBackgroubd: ViewTreeObserver.OnGlobalLayoutListener
    private lateinit var listenerForeground: ViewTreeObserver.OnGlobalLayoutListener
    private var backgroundRes: Int = 0
    private var foregroundRes: Int = 0
    private var foregroundFinal: Int = 0

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
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.StyledProgressBar)
        backgroundRes = typedArray.getInt(R.styleable.StyledProgressBar_backgroundLayer, R.drawable.bg_progress_background)
        foregroundRes = typedArray.getInt(R.styleable.StyledProgressBar_foregroundLayer, R.drawable.bg_progress_foreground)
        foregroundFinal = typedArray.getInt(R.styleable.StyledProgressBar_foregroundFinalLaer, R.drawable.bg_progress_foreground)
        LayoutInflater.from(context).inflate(R.layout.layout_styled_progress_bar, this)
        typedArray.recycle()
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        vBackground = findViewById(R.id.v_background)
        vForeground = findViewById(R.id.v_foreground)
        tvFinalValue = findViewById(R.id.tv_final_position)
        tvInitialValue = findViewById(R.id.tv_initial_position)
        tvTarget = findViewById(R.id.tv_target_position)
        clContainer = findViewById(R.id.c_progress_container)

        vBackground.background = resources.getDrawable(backgroundRes)
        vForeground.background = resources.getDrawable(foregroundRes)

        val observable = vBackground.viewTreeObserver

        listenerBackgroubd = ViewTreeObserver.OnGlobalLayoutListener {
            currentWidth = vBackground.width
            vBackground.viewTreeObserver.removeOnGlobalLayoutListener(listenerBackgroubd)
        }

        observable.addOnGlobalLayoutListener(listenerBackgroubd)

        val observableForeground = vForeground.viewTreeObserver

        listenerForeground = ViewTreeObserver.OnGlobalLayoutListener {
            currentMinWidth = vForeground.width
            vBackground.viewTreeObserver.removeOnGlobalLayoutListener(listenerForeground)
        }

        observableForeground.addOnGlobalLayoutListener(listenerForeground)
    }

    fun setProgress(progress: Int) {
        vForeground.layoutParams.width = calculateProgress(progress)
    }

    fun setInitialValue(initial: String){
        tvInitialValue.text = initial
    }

    fun setFinalValue(final: String){
        tvTarget.text = final
        tvFinalValue.text = final
    }

    fun setBaseBackground(resId: Int){
        backgroundRes = resId
        vBackground.background = resources.getDrawable(resId)
    }

    fun setBaseForeground(resId: Int){
        foregroundRes = resId
        vForeground.background = resources.getDrawable(resId)
    }

    fun setCompleteForeground(resId: Int){
        foregroundFinal = resId
    }

    fun setInitialWidth(width: Int){
        currentWidth = width
    }

    fun setHeight(height: Int){
        clContainer.layoutParams.height = height
    }

    private fun calculateProgress(progress: Int): Int {
        val progressWidth = currentWidth * (progress / 100f)

        if(currentWidth - progressWidth < currentMinWidth){
            tvTarget.visibility = View.GONE
            tvFinalValue.visibility = View.VISIBLE
        }else{
            tvTarget.visibility = View.VISIBLE
            tvFinalValue.visibility = View.GONE
        }

        if(progress == 100){
            tvInitialValue.setTextColor(resources.getColor(R.color.general_transparent_text_white))
            vForeground.background = resources.getDrawable(foregroundFinal)
            vBackground.background = resources.getDrawable(foregroundFinal)
        }else{
            tvInitialValue.setTextColor(resources.getColor(R.color.general_white))
            vForeground.background = resources.getDrawable(foregroundRes)
        }

        return Math.max(progressWidth.toInt(), currentMinWidth)
    }
}