package com.github.kiolk.alphabet.data.source.player

import com.github.kiolk.alphabet.data.models.player.Player
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class RealPlayerrepository
@Inject
constructor(private val local: PlayerDataSource) : PlayerRepository {

    override fun addPlayer(player: Player): Completable = local.addPlayer(player)

    override fun updatePlayer(player: Player): Completable = local.updatePlayer(player)

    override fun getPlayer(playerName: String): Single<Player> = local.getPlayer(playerName)

    override fun getCurrentPlayer(playerName: String): Flowable<Player> = local.getCurrentPlayer(playerName)
}