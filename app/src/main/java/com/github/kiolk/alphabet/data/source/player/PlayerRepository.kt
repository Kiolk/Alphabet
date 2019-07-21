package com.github.kiolk.alphabet.data.source.player

import com.github.kiolk.alphabet.data.models.player.Player
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface PlayerRepository {

    fun addPlayer(player: Player): Completable

    fun updatePlayer(player: Player): Completable

    fun getPlayer(playerName: String): Single<Player>

    fun getCurrentPlayer(playerName: String): Flowable<Player>
}