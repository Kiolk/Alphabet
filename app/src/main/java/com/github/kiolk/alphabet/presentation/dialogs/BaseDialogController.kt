package com.github.kiolk.alphabet.presentation.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import butterknife.ButterKnife
import butterknife.Unbinder
import com.github.kiolk.alphabet.R
import com.mkhytarmkhoian.conductor.dialog.DialogController

abstract class BaseDialogController(args: Bundle) : DialogController(args) {

    private var unbinder: Unbinder? = null

    override fun inflateDialogView(inflater: LayoutInflater, savedViewState: Bundle?): View? = inflater.inflate(getLayout(), null, false)

    override fun onCreateDialog(): Dialog {
        val dialog = super.onCreateDialog()
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    override fun onContextAvailable(context: Context) {
        super.onContextAvailable(context)
        setStyle(STYLE_NORMAL, R.style.AppTheme_Dialog)
    }

    override fun onBindDialogView(view: View, savedViewState: Bundle?) {
        unbinder = ButterKnife.bind(this, view)
        onViewBound(view)
    }

    override fun onDestroy() {
        unbinder?.unbind()
        unbinder = null
        super.onDestroy()
    }


    override fun setupWindow(view: View?, window: Window?) {
        super.setupWindow(view, window)

        window?.let {
            val params = WindowManager.LayoutParams()
            params.copyFrom(it.attributes)
            params.windowAnimations = R.style.AppTheme_Dialog
            params.dimAmount = 0.7f
            params.flags = params.flags or WindowManager.LayoutParams.FLAG_DIM_BEHIND
            it.attributes = params
        }
    }

    open fun onViewBound(view: View) {}

    abstract fun getLayout(): Int
}