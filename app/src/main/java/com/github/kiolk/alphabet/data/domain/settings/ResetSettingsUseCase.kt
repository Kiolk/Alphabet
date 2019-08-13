package com.github.kiolk.alphabet.data.domain.settings

import com.github.kiolk.alphabet.data.domain.UseCase
import com.github.kiolk.alphabet.data.source.settings.SettingsRepository
import io.reactivex.Completable
import javax.inject.Inject

class ResetSettingsUseCase
@Inject
constructor(private val settingsRepository: SettingsRepository) : UseCase<Completable, ResetSettingsUseCase.Params> {

    override fun execute(params: Params): Completable {
        return settingsRepository.getBackup()
                .flatMapCompletable { settings -> settingsRepository.setSettings(settings) }
    }

    class Params
}