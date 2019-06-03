package com.github.kiolk.alphabet.data.source.settings.local

import com.github.kiolk.alphabet.data.models.game.GameSettings
import com.github.kiolk.alphabet.data.source.settings.SettingsDataSource
import io.reactivex.Completable
import io.reactivex.Flowable
import java.lang.Exception
import javax.inject.Inject


class LocalSettingsDataSource
@Inject
constructor(private val settingDao: SettingsDao) : SettingsDataSource {

    override fun setSettings(settings: List<GameSettings>): Completable {
        return Completable.create {
            try {
                settingDao.setSettings(settings)
                it.onComplete()
            } catch (e: Exception) {
                it.onError(e)
            }
        }
    }

    override fun getAllSettings(): Flowable<List<GameSettings>> {
        return settingDao.getAllSettings()
    }
}