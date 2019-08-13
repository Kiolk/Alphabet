package com.github.kiolk.alphabet.data.source.settings

import com.github.kiolk.alphabet.data.models.game.GameSettings
import com.github.kiolk.alphabet.data.models.letter.Letter
import com.github.kiolk.alphabet.di.qualifaiers.LocalDataSource
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
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

    override fun getSettingsByLatter(letter: Letter): Flowable<List<GameSettings>> {
        return local.getSettingsByLatter(letter)
    }

    override fun updateSetting(gameSetting: GameSettings): Completable {
        return local.updateSAetting(gameSetting)
    }

    override fun getNextAvailableSettings(gameSetting: GameSettings): Single<Pair<GameSettings?, GameSettings?>> {
        return local.getNextAvailableSettings(gameSetting)
    }

    override fun getBackup(): Single<List<GameSettings>> = local.getBackup()

    override fun setBackUp(settings: List<GameSettings>): Completable = local.setBackUp(settings)
}