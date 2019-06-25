package com.github.kiolk.alphabet.data.domain

import com.github.kiolk.alphabet.data.models.game.GameSettings
import com.github.kiolk.alphabet.data.source.settings.SettingsRepository
import io.reactivex.Single
import javax.inject.Inject

class UpdateGameUseCase
@Inject
constructor(private val settingsRepository: SettingsRepository): UseCase<Single<GameSettings>, UpdateGameUseCase.Params>{

    override fun execute(params: Params): Single<GameSettings> {
        return settingsRepository.updateSetting(params.settings)
                .andThen(settingsRepository.getNextAvailableSettings(params.settings))
    }

    data class Params(val settings: GameSettings)
}