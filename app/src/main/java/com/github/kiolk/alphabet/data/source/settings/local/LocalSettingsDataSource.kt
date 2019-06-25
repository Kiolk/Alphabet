package com.github.kiolk.alphabet.data.source.settings.local

import com.github.kiolk.alphabet.data.models.game.GameSettings
import com.github.kiolk.alphabet.data.models.letter.Letter
import com.github.kiolk.alphabet.data.models.throwables.NoGameSettings
import com.github.kiolk.alphabet.data.source.settings.SettingsDataSource
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
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

    override fun getSettingsByLatter(letter: Letter): Flowable<List<GameSettings>> {
        return settingDao.getSettingsByLetter(letter.letter)
    }

    override fun updateSAetting(gameSetting: GameSettings): Completable {
        return Completable.create {
            try {settingDao.updateSAetting(gameSetting)
                it.onComplete()
            }catch (ex: Throwable){
                it.onError(ex)
            }}
    }

    override fun getNextAvailableSettings(gameSetting: GameSettings): Single<GameSettings> {
        return Single.create {
            try {
                val next = settingDao.getNextAvailableSettings(gameSetting.gameSchema.letterValue)
                val nestGame = next.firstOrNull( {setting -> !setting.isAvailable && !setting.isCompleted})

                if(nestGame != null){
                    it.onSuccess(nestGame)
                }else{
                    it.onError(NoGameSettings())
                }
            }catch (e: Throwable){
                it.onError(e)
            }
        }
    }
}