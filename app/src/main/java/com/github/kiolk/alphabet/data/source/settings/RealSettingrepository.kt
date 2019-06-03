package com.github.kiolk.alphabet.data.source.settings

import com.github.kiolk.alphabet.data.models.game.GameSettings
import com.github.kiolk.alphabet.di.qualifaiers.LocalDataSource
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class  RealSettingrepository
@Inject
constructor(@LocalDataSource private val local : SettingsDataSource) : SettingsRepository{

    override fun setSettings(settings: List<GameSettings>): Completable {
        return local.setSettings(settings)
    }

    override fun getAllSettings(): Flowable<List<GameSettings>> {
        return local.getAllSettings()
    }
}