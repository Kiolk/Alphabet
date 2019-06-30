package com.github.kiolk.alphabet.presentation.dialogs

import android.os.Bundle
import com.github.kiolk.alphabet.R

class EndGameDialog(args: Bundle): BaseInfoDialog(args) {

    override fun getLayout(): Int {
        return R.layout.controller_game_result
    }
}