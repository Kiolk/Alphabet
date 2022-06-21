package com.github.kiolk.alphabet.data.source.settings.local

import android.util.Log
import com.github.kiolk.alphabet.data.models.letter.Letter
import com.github.kiolk.alphabet.data.source.settings.SettingsDataSource
import com.github.kiolk.common.data.model.word.GameSettings
import com.github.kiolk.common.data.model.word.toBackupSettings
import com.github.kiolk.common.data.model.word.toGameSattings
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class LocalSettingsDataSource
@Inject
constructor(private val settingDao: SettingsDao, private val backupDao: BackupSettingDao) : SettingsDataSource {

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

    override fun getNextAvailableSettings(gameSetting: GameSettings): Single<Pair<GameSettings?, GameSettings?>> {
        return Single.create {
            try {
                val next = settingDao.getNextAvailableSettings(gameSetting.gameSchema.letterValue)
                val previewsGame = next.firstOrNull { setting -> setting.level < gameSetting.level}
                val nextGame = next.firstOrNull{ setting -> setting.level > gameSetting.level && gameSetting.isCompleted}
//                val nestGame = next.firstOrNull { setting -> !setting.isAvailable && !setting.isCompleted}
                it.onSuccess(Pair(previewsGame, nextGame))
//                if(nestGame != null){
//
//                }else{
//                    it.onError(NoGameSettings())
//                }
            }catch (e: Throwable){
                it.onError(e)
            }
        }
    }

    override fun getBackup(): Single<List<GameSettings>> {
        return backupDao.getBackupSetting().map { settings ->
            settings.map { it.toGameSattings() }
        }
    }

    override fun setBackUp(settings: List<GameSettings>): Completable {
        Log.d("MyLogs", settings.size.toString())
        return Completable.create{
            try {
                backupDao.setSettingBackup(settings.map { it.toBackupSettings() })
                it.onComplete()
            } catch (ex: Exception) {
                Log.e("MyLogs", ex.message, ex)
                it.onError(ex)
            }
        }
    }
}