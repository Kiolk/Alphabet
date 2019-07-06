package com.github.kiolk.alphabet.data.domain.words

import com.github.kiolk.alphabet.data.domain.UseCase
import com.github.kiolk.alphabet.data.models.letter.Letter
import com.github.kiolk.alphabet.data.source.settings.SettingsRepository
import com.github.kiolk.alphabet.utils.Data.alphabet
import io.reactivex.Flowable
import javax.inject.Inject

class GetAlphabetUseCase
@Inject
constructor(private val settingsRepository: SettingsRepository) : UseCase<Flowable<List<Letter>>, GetAlphabetUseCase.Params> {

    override fun execute(params: Params): Flowable<List<Letter>> {
        return settingsRepository.getAllSettings()
                .map { settings ->
                    alphabet.forEach { letter ->
                        var completedCount = 0
                        settings.filter { setting -> setting.gameSchema.letterValue == letter.letterValue }.let { filteredSettings ->
                            for (item in filteredSettings) {
                                if (item.isCompleted) ++completedCount
                            }
                            letter.completedLevel = if(filteredSettings.isNotEmpty()) completedCount / filteredSettings.size.toFloat() else -1f
                        }
                    }
                    return@map alphabet
                }
    }

    class Params
}