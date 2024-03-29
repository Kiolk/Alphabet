package com.github.kiolk.alphabet.presentation.dialogs

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.OnClick
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.data.models.game.GameStats

class EndGameDialog : BaseInfoDialog {

    @BindView(R.id.btn_end_game_repeat)
    lateinit var ivbRepeat: ImageButton

    @BindView(R.id.btn_end_game_next)
    lateinit var ivbNext: ImageButton

    @BindView(R.id.btn_end_game_preview)
    lateinit var ivbPreview: ImageButton

    @BindView(R.id.tv_game_end_result)
    lateinit var tvResult: TextView

    @BindView(R.id.iv_left_start)
    lateinit var ivLeftStar: ImageView

    @BindView(R.id.iv_right_star)
    lateinit var ivRightStar: ImageView

    @BindView(R.id.iv_central_star)
    lateinit var ivCentralStar: ImageView

    lateinit var listener: OnEndDialogClickListener

    constructor(args: Bundle, listener: OnEndDialogClickListener) : super(args) {
        this.listener = listener
    }

    constructor(args: Bundle) : super(args)

    override fun getLayout(): Int {
        return R.layout.controller_end_game
    }

    override fun onViewBound(view: View) {
        super.onViewBound(view)

        dialog?.let { isCancelable = false }
        val stats = args.getParcelable(BUNDLE_GAME_STATS) as? GameStats ?: return

//        tvResult.text =  activity?.baseContext?.resources?.getString(R.string.end_game_not_complete, "${stats.asked.toString()}/${stats.correct.toString()}")

        when (stats.stars) {
            0 -> {
                tvResult.text = activity?.baseContext?.resources?.getString(R.string.end_game_not_complete, stats.correct.toString(), stats.asked.toString())
                ivCentralStar.setImageDrawable(resources?.getDrawable(R.drawable.ic_gray_star))
                ivRightStar.setImageDrawable(resources?.getDrawable(R.drawable.ic_gray_star))
                ivLeftStar.setImageDrawable(resources?.getDrawable(R.drawable.ic_gray_star))
            }
            1 -> {
                tvResult.text = activity?.baseContext?.resources?.getString(R.string.end_game_complete, stats.correct.toString(), stats.asked.toString())
                ivCentralStar.setImageDrawable(resources?.getDrawable(R.drawable.ic_gray_star))
                ivRightStar.setImageDrawable(resources?.getDrawable(R.drawable.ic_gray_star))
            }
            2 -> {
                tvResult.text = activity?.baseContext?.resources?.getString(R.string.end_game_good, stats.correct.toString(), stats.asked.toString())
                ivRightStar.setImageDrawable(resources?.getDrawable(R.drawable.ic_gray_star))
            }
            3 -> {
                tvResult.text = activity?.baseContext?.resources?.getString(R.string.end_game_excellent, stats.correct.toString(), stats.asked.toString())
            }
        }

        if (stats.isPreview) {
            ivbPreview.visibility = View.VISIBLE
        }

        if (stats.isNext) {
            ivbNext.visibility = View.VISIBLE
        }
    }

    interface OnEndDialogClickListener {
        fun onRepeat()

        fun onNext()

        fun onPreview()
    }

    @OnClick(R.id.btn_end_game_next)
    fun onNextClick() {
        listener.onNext()
        router.popController(this)
    }

    @OnClick(R.id.btn_end_game_repeat)
    fun onRepeatClick() {
        listener.onRepeat()
        router.popController(this)
    }

    @OnClick(R.id.btn_end_game_close)
    fun onCloseClick(){
        router.popToRoot()
    }

    @OnClick(R.id.btn_end_game_preview)
    fun onPreviewClick() {
        listener.onPreview()
        router.popController(this)
    }

    companion object {

        private const val BUNDLE_GAME_STATS = "BUNDLE_GAME_STATS"
        const val TAG = "EndGameDialog"

        fun getInstance(current: GameStats, listener: OnEndDialogClickListener): EndGameDialog {
            val bundle = Bundle()
            bundle.putParcelable(BUNDLE_GAME_STATS, current)
            return EndGameDialog(bundle, listener)
        }
    }
}