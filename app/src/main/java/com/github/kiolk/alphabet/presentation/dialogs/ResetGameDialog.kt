package com.github.kiolk.alphabet.presentation.dialogs

import android.os.Bundle
import android.view.View
import butterknife.OnClick
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.data.domain.player.ResetGameUseCase
import com.github.kiolk.alphabet.utils.RxSchedulerProvider
import io.reactivex.disposables.Disposable
import timber.log.Timber
import javax.inject.Inject

class ResetGameDialog: BaseInfoDialog {

    override fun getLayout(): Int = R.layout.controller_reset_game

    lateinit var listener: () -> Unit

    constructor(args: Bundle, listener: () -> Unit): super(args){
        this.listener = listener
    }

    constructor(args: Bundle): super(args)

    override fun onViewBound(view: View) {
        super.onViewBound(view)

        dialog.let { isCancelable = true }
    }

    @OnClick(R.id.btn_reset_game_confirm)
    fun onConfirmPress(){
        router.popCurrentController()
        listener.invoke()
    }

    @OnClick(R.id.btn_reset_game_cancel)
    fun onCancelPress(){
        router.popController(this)
    }

    companion object{
        fun getInstance(listener: () -> Unit): ResetGameDialog{
            return ResetGameDialog(Bundle(), listener)
        }

        const val TAG = "ResetGameDialog"
    }
}