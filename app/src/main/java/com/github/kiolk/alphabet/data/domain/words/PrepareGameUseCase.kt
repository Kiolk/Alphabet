package com.github.kiolk.alphabet.data.domain.words

import com.github.kiolk.alphabet.data.domain.PrepareGameSetUseCase
import com.github.kiolk.alphabet.data.models.game.GameResult
import com.github.kiolk.common.data.model.word.GameSettings
import com.github.kiolk.common.domain.base.UseCase
import com.github.kiolk.common.domain.repository.word.WordsRepository
import io.reactivex.Single
import javax.inject.Inject

class PrepareGameUseCase
@Inject
constructor(
    private val repository: WordsRepository,
    private val prepareGameSetUseCase: PrepareGameSetUseCase
) :
    UseCase<Single<GameResult>, PrepareGameUseCase.Params> {


    override fun execute(params: Params): Single<GameResult> {
        return repository.selectWords(params.settings)
            .firstOrError()
            .map { list ->
                GameResult(
                    params.settings,
                    null,
                    prepareGameSetUseCase.execute(
                        PrepareGameSetUseCase.Params(
                            params.settings.numberAskedWords,
                            list
                        )
                    ).toTypedArray(),
                    0,
                    0
                )
            }
    }

    data class Params(val settings: GameSettings)
}