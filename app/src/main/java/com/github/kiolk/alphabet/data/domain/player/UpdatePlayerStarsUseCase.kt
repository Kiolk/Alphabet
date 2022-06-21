package com.github.kiolk.alphabet.data.domain.player

import com.github.kiolk.alphabet.data.source.player.PlayerRepository
import com.github.kiolk.common.domain.base.UseCase
import io.reactivex.Completable
import javax.inject.Inject

class UpdatePlayerStarsUseCase
@Inject
constructor(private val playerRepository: PlayerRepository) :
    UseCase<Completable, UpdatePlayerStarsUseCase.Params> {

    override fun execute(params: Params): Completable {
        return playerRepository.getPlayer("Main")
            .flatMapCompletable { player -> playerRepository.updatePlayer(player.copy(stars = player.stars + params.stars)) }
    }

    data class Params(val stars: Int)
}