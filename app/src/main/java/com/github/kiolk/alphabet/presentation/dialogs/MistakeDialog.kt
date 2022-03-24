package com.github.kiolk.alphabet.presentation.dialogs

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import butterknife.BindView
import butterknife.OnClick
import com.bluelinelabs.conductor.Controller
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.data.models.game.GameItem
import com.github.kiolk.alphabet.presentation.game.game.MistakePablisher
import com.github.kiolk.alphabet.utils.getContext
import com.github.kiolk.alphabet.utils.hideKeyboardImplicit
import com.github.kiolk.alphabet.utils.showKeyboardImplicit
import java.net.UnknownHostException

class MistakeDialog : BaseInfoDialog {

    constructor(args: Bundle, target: Controller) : this(args) {
        targetController = target
    }

    constructor(args: Bundle) : super(args)

    @BindView(R.id.tv_mistake_title)
    lateinit var tvMistakeTitle: TextView

    @BindView(R.id.et_mistake_description)
    lateinit var etMistakeDescription: EditText

    @BindView(R.id.pb_publish)
    lateinit var pbProgress: ProgressBar

    @BindView(R.id.btn_cancel_mistake)
    lateinit var tvCancel: TextView

    @BindView(R.id.btn_publish_mistake)
    lateinit var tvPublush: TextView

    private var isPublished: Boolean = false

    override fun getLayout(): Int = R.layout.controller_mistake_dialog

    override fun onViewBound(view: View) {
        super.onViewBound(view)

        dialog?.let { isCancelable = true }
        val gameItem: GameItem = args.getParcelable(BUNDLE_GAME_ITEM) as? GameItem ?: return
        val text = getContext().resources.getString(
            R.string.mistake_title,
            gameItem.currentWord.value.capitalize()
        )
        showKeyboardImplicit()

        val span = SpannableString(text)
        span.setSpan(ForegroundColorSpan(view.context.resources.getColor(R.color.general_blue)), text.indexOfFirst { it == '"' } + 1, text.indexOfLast { it == '"' }, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        tvMistakeTitle.text = span
    }

    @OnClick(R.id.btn_publish_mistake)
    fun onPublish() {
        if (isPublished) {
            router.popCurrentController()
        } else {
            pbProgress.visibility = View.VISIBLE
            (targetController as MistakePablisher).publishMistake(args.getParcelable<GameItem>(BUNDLE_GAME_ITEM)?.currentWord?.value ?: "", etMistakeDescription.text.toString())
        }
    }

    @OnClick(R.id.btn_cancel_mistake)
    fun onCancel() {
        etMistakeDescription.hideKeyboardImplicit()
        router.popCurrentController()
    }

    fun showSuccess() {
        showFinalMessage()
        tvMistakeTitle.setText(R.string.success_publish)
    }

    fun showError(throwable: Throwable) {
        val res = if (throwable is UnknownHostException) {
            R.string.no_internet_connection_error
        } else {
            R.string.unknown_error
        }
        showFinalMessage()
        tvMistakeTitle.setText(res)
    }


    private fun showFinalMessage() {
        pbProgress.visibility = View.INVISIBLE
        etMistakeDescription.hideKeyboardImplicit()
        etMistakeDescription.visibility = View.GONE
        tvCancel.visibility = View.INVISIBLE
        tvPublush.setText(R.string.ok)
        isPublished = true
    }

    companion object {
        const val TAG = "MistakeDialog"
        private const val BUNDLE_GAME_ITEM = "BUNDLE_GAME_ITEM"

        fun newInstance(item: GameItem, target: Controller): MistakeDialog {
            val bundle = Bundle()
            bundle.putParcelable(BUNDLE_GAME_ITEM, item)
            return MistakeDialog(bundle, target)
        }
    }
}