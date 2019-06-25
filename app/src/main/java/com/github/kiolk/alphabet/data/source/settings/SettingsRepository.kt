package com.github.kiolk.alphabet.data.source.settings

import com.github.kiolk.alphabet.data.models.game.GameSettings
import com.github.kiolk.alphabet.data.models.letter.Letter
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface SettingsRepository {

    fun setSettings(settings : List<GameSettings>) : Completable

    fun getAllSettings() : Flowable<List<GameSettings>>

    fun getSettingsByLatter(letter: Letter): Flowable<List<GameSettings>>

    fun updateSetting(gameSetting: GameSettings): Completable

    fun getNextAvailableSettings(gameSetting: GameSettings): Single<GameSettings>
}