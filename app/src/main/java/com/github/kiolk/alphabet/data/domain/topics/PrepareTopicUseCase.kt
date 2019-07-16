package com.github.kiolk.alphabet.data.domain.topics

import com.github.kiolk.alphabet.data.domain.PrepareGameSetUseCase
import com.github.kiolk.alphabet.data.domain.UseCase
import com.github.kiolk.alphabet.data.models.game.GameResult
import com.github.kiolk.alphabet.data.models.game.GameSettings
import com.github.kiolk.alphabet.data.models.topic.Topic
import com.github.kiolk.alphabet.data.source.words.WordsRepository
import io.reactivex.Single
import javax.inject.Inject

class PrepareTopicUseCase
@Inject
constructor(private val repository: WordsRepository,
            private val prepareGameSetUseCase: PrepareGameSetUseCase) : UseCase<Single<GameResult>, PrepareTopicUseCase.Params> {


    override fun execute(params: Params): Single<GameResult> {
        return repository.getTopicWords(params.topic)
                .firstOrError()
                .map { list ->

                    var asked = params.topic.total - params.topic.read
                    asked = if(asked == 0) params.topic.total else if(asked < 3) 4 else asked
                    GameResult(null, params.topic, prepareGameSetUseCase.execute(PrepareGameSetUseCase.Params(asked, list)).toTypedArray(), 0, 0)

                }
    }


data class Params(val topic: Topic)
}