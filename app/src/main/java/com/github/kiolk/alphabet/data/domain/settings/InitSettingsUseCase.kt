package com.github.kiolk.alphabet.data.domain.settings

import com.github.kiolk.alphabet.data.models.game.GameSettings
import com.github.kiolk.alphabet.data.source.settings.SettingsRepository
import com.github.kiolk.common.domain.base.UseCase
import io.reactivex.Completable
import javax.inject.Inject

class InitSettingsUseCase
@Inject
constructor(private val settingsRepository: SettingsRepository) :
    UseCase<Completable, InitSettingsUseCase.Params> {

    override fun execute(params: Params): Completable {
        return settingsRepository.setSettings(params.settings)
            .andThen(settingsRepository.setBackUp(params.settings))
    }

    data class Params(val settings: List<GameSettings>)
}