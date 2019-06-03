package com.github.kiolk.alphabet.data.source.settings

import com.github.kiolk.alphabet.data.models.game.GameSettings
import io.reactivex.Completable
import io.reactivex.Flowable

interface SettingsDataSource {

    fun setSettings(settings : List<GameSettings>) : Completable

    fun getAllSettings() : Flowable<List<GameSettings>>
}