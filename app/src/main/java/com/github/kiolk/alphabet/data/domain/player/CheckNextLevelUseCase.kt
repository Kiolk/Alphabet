package com.github.kiolk.alphabet.data.domain.player

import android.util.Log
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.data.domain.UseCase
import com.github.kiolk.alphabet.data.models.level.Level
import com.github.kiolk.alphabet.data.models.level.LevelType
import com.github.kiolk.alphabet.data.models.level.LevelTypes
import com.github.kiolk.alphabet.data.source.levels.LevelRepository
import com.github.kiolk.alphabet.data.source.player.PlayerRepository
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

class CheckNextLevelUseCase
@Inject
constructor(private val playerRepository: PlayerRepository,
            private val levelRepository: LevelRepository) : UseCase<Single<Pair<LevelType?, Int>>, CheckNextLevelUseCase.Params> {

    override fun execute(params: Params): Single<Pair<LevelType?, Int>> {
        return playerRepository.getPlayer("Main")
                .flatMap { player ->
                    playerRepository.updatePlayer(player.copy(stars = player.stars + params.stars)).`as` {
                        return@`as` playerRepository.getPlayer("Main")
                                .flatMap { player ->
//                                    val nextLevel = LevelTypes.acceptNextLevel(player.stars, player.stars + params.stars)
                                    val nextLevel = levelRepository.acceptNextLevel(player.stars, player.stars + params.stars)
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