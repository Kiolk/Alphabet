package com.github.kiolk.alphabet.data.domain.player

import com.github.kiolk.alphabet.data.source.levels.LevelRepository
import com.github.kiolk.alphabet.data.source.player.PlayerRepository
import com.github.kiolk.common.data.model.level.LevelType
import com.github.kiolk.common.domain.base.UseCase
import io.reactivex.Single
import javax.inject.Inject

class CheckNextLevelUseCase
@Inject
constructor(
    private val playerRepository: PlayerRepository,
    private val levelRepository: LevelRepository
) :
    UseCase<Single<Pair<LevelType?, Int>>, CheckNextLevelUseCase.Params> {

    override fun execute(params: Params): Single<Pair<LevelType?, Int>> {
        return playerRepository.getPlayer("Main")
            .flatMap { player ->
                playerRepository.updatePlayer(player.copy(stars = player.stars + params.stars))
                    .`as` {
                        return@`as` playerRepository.getPlayer("Main")
                            .flatMap { player ->
//                                    val nextLevel = LevelTypes.acceptNextLevel(player.stars, player.stars + params.stars)
                                val nextLevel = levelRepository.acceptNextLevel(
                                    player.stars,
                                    player.stars + params.stars
                                )
                                if (nextLevel != null) {
                                        return@flatMap Single.just(Pair<LevelType, Int>(nextLevel, params.stars))
                                    }
                                    return@flatMap Single.just(Pair<LevelType?, Int>(null, params.stars))
                                }
                    }
                }
    }

    data class Params(val stars: Int)
}