package com.github.kiolk.alphabet.data.domain

import com.github.kiolk.alphabet.data.models.game.GameSettings
import com.github.kiolk.alphabet.data.source.settings.SettingsRepository
import com.github.kiolk.common.domain.base.UseCase
import io.reactivex.Single
import javax.inject.Inject

class UpdateGameUseCase
@Inject
constructor(private val settingsRepository: SettingsRepository) :
    UseCase<Single<Pair<GameSettings?, GameSettings?>>, UpdateGameUseCase.Params> {

    override fun execute(params: Params): Single<Pair<GameSettings?, GameSettings?>> {
        return settingsRepository.updateSetting(params.settings)
            .andThen(settingsRepository.getNextAvailableSettings(params.settings))
    }

    data class Params(val settings: GameSettings)
}