package com.github.kiolk.alphabet.data

import android.content.Context
import android.media.AudioManager
import android.media.SoundPool
import com.github.kiolk.alphabet.R

class SoundManager(val context: Context) {

    lateinit var soundPool: SoundPool
    var correctSound: Int
    var wrongSound: Int
    var winSound: Int

    init {
        soundPool = SoundPool(3, AudioManager.STREAM_MUSIC, 1)
        correctSound = soundPool.load(context, R.raw.end_game, 1)
        wrongSound = soundPool.load(context, R.raw.wrong, 1)
        winSound = soundPool.load(context, R.raw.correct, 1)
    }

    fun playCorrect() {
        soundPool.play(correctSound, 1f, 1f, 1, 0, 1f)
    }

    fun playWrong() {
        soundPool.play(wrongSound, 1f, 1f, 1, 0, 1f)
    }

    fun playWin() {
        soundPool.play(winSound, 1f, 1f, 1, 0, 1f)
    }
}
