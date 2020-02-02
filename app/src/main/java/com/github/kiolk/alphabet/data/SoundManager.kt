package com.github.kiolk.alphabet.data

import android.content.Context
import android.content.SharedPreferences
import android.media.AudioManager
import android.media.SoundPool
import com.github.kiolk.alphabet.R

class SoundManager(val context: Context, val sharedPreferences: SharedPreferences) {

    private var soundPool: SoundPool = SoundPool(3, AudioManager.STREAM_MUSIC, 1)
    private var correctSound: Int
    private var wrongSound: Int
    private var winSound: Int
    var isOff: Boolean

    init {
        correctSound = soundPool.load(context, R.raw.end_game, 1)
        wrongSound = soundPool.load(context, R.raw.wrong, 1)
        winSound = soundPool.load(context, R.raw.correct, 1)

        isOff = sharedPreferences.getBoolean(KEY_SOUND_OFF, false)
    }

    fun playCorrect() {
        if (isOff) {
            return
        }

        soundPool.play(correctSound, 1f, 1f, 1, 0, 1f)
    }

    fun playWrong() {
        if (isOff) {
            return
        }

        soundPool.play(wrongSound, 1f, 1f, 1, 0, 1f)
    }

    fun playWin() {
        if (isOff) {
            return
        }

        soundPool.play(winSound, 1f, 1f, 1, 0, 1f)
    }

    fun changeSoundState(): Boolean {
        isOff = !isOff
        sharedPreferences.edit().putBoolean(KEY_SOUND_OFF, isOff).apply()
        return isOff
    }

    companion object {
        private const val KEY_SOUND_OFF = "KEY_SOUND_OFF"
    }
}
