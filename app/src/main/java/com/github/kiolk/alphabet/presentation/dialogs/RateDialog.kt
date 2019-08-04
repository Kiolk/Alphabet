package com.github.kiolk.alphabet.presentation.dialogs

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.OnClick
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.data.domain.levels.RateUseCase
import javax.inject.Inject

class RateDialog : BaseInfoDialog(Bundle()){

    override fun getLayout(): Int = R.layout.controller_rate_dialog

    override fun onViewBound(view: View) {
        super.onViewBound(view)

        dialog?.let { isCancelable = true }
    }

    @OnClick(R.id.btn_rate_now)
    fun onRateLate() {
        val rateUseCase = activity?.baseContext?.let { RateUseCase(it) }
        rateUseCase?.execute(RateUseCase.Params())
    }

    @OnClick(R.id.btn_rate_later)
    fun onRateNow() {
        router.popCurrentController()
    }

    companion object {
        const val TAG = "RateDialog"
    }
}