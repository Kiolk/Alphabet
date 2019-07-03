package com.github.kiolk.alphabet.presentation.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.utils.getWindowWidth
import com.github.kiolk.alphabet.utils.toPx

abstract class BaseInfoDialog(args: Bundle): BaseDialogController(args) {

    private var width = 0

    override fun onContextAvailable(context: Context) {
        super.onContextAvailable(context)
        width = Math.min(340.toPx, context.getWindowWidth() - 40.toPx)
    }

    override fun onCreateDialog(): Dialog = super.onCreateDialog().apply { setContentView(getLayout()) }

    override fun setupWindow(view: View?, window: Window?) {
        super.setupWindow(view, window)

        window?.let {
            val params = WindowManager.LayoutParams()
            params.copyFrom(it.attributes)
            params.width = width
            it.attributes = params
        }
    }
}