package com.github.kiolk.alphabet.data.domain.levels

import com.github.kiolk.alphabet.data.domain.UseCase
import com.github.kiolk.alphabet.data.models.level.LevelTypes
import com.github.kiolk.alphabet.data.source.levels.LevelRepository
import com.github.kiolk.alphabet.data.source.settings.SettingsRepository
import com.github.kiolk.common.data.model.level.LevelType
import io.reactivex.Completable
import javax.inject.Inject

class ConfigureLevelsUseCase
@Inject
constructor(private val settingsRepository: SettingsRepository,
            private val levelRepository: LevelRepository) : UseCase<Completable, ConfigureLevelsUseCase.Params> {

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

                    val configureLevels = mutableListOf<LevelType>()
                    LevelTypes.values().forEach { level ->
                        var item = LevelType(level.level.image, level.level.title, counter, level.sentence, level.author)
                        counter += gamePerLevel

                        if (lastLevel == level) {
                            item = LevelType(level.level.image, level.name, counter + restOfGames, "", "")
                        }

                        configureLevels.add(item)
                    }

                    levelRepository.addLevels(configureLevels)
                    return@flatMapCompletable Completable.complete()
                }
    }

    class Params
}