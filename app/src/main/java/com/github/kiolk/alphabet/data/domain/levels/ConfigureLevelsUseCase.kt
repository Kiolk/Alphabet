package com.github.kiolk.alphabet.data.domain.levels

import com.github.kiolk.alphabet.data.domain.UseCase
import com.github.kiolk.alphabet.data.models.level.LevelTypes
import com.github.kiolk.alphabet.data.source.settings.SettingsRepository
import io.reactivex.Completable
import javax.inject.Inject

class ConfigureLevelsUseCase
@Inject
constructor(private val settingsRepository: SettingsRepository) : UseCase<Completable, ConfigureLevelsUseCase.Params> {

    override fun execute(params: Params): Completable {
        return settingsRepository.getAllSettings()
                .take(1)
                .flatMapCompletable { settings ->
                    val games = settings.size * 3
                    val levels = LevelTypes.values().size
                    val gamePerLevel = games / levels
                    val restOfGames = games - levels * gamePerLevel
                    var counter = 0
                    val lastLevel = LevelTypes.values().last()
                    LevelTypes.values().forEach { level ->
                        LevelTypes.getLevel(level.level).needStars = counter
                        level.needStars = counter
                        counter += gamePerLevel

                        if (lastLevel == level) {
                            LevelTypes.getLevel(level.level).needStars = counter + restOfGames
                        }
                    }
                    return@flatMapCompletable Completable.complete()
                }
    }

    class Params
}