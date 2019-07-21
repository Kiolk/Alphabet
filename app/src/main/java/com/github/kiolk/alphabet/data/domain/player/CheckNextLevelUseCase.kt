package com.github.kiolk.alphabet.data.domain.player

import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.data.domain.UseCase
import com.github.kiolk.alphabet.data.models.level.Level
import com.github.kiolk.alphabet.data.models.level.LevelTypes
import com.github.kiolk.alphabet.data.source.player.PlayerRepository
import io.reactivex.Single
import javax.inject.Inject

class CheckNextLevelUseCase
@Inject
constructor(private val playerRepository: PlayerRepository): UseCase<Single<Level>, CheckNextLevelUseCase.Params > {

    override fun execute(params: Params): Single<Level> {
        return playerRepository.getPlayer("Main")
                .flatMap{player ->
                    val nextLevel = LevelTypes.acceptNextLevel(player.stars,player.stars + params.stars)
                    if(nextLevel != null){
                        return@flatMap Single.just(nextLevel.level)
                    }

                    throw error("Not found")
                }
    }

    data class Params(val stars: Int)
}