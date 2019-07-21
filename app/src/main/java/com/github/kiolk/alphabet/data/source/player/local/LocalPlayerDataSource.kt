package com.github.kiolk.alphabet.data.source.player.local

import com.github.kiolk.alphabet.data.models.player.Player
import com.github.kiolk.alphabet.data.source.player.PlayerDataSource
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import org.intellij.lang.annotations.Flow
import javax.inject.Inject

class LocalPlayerDataSource
@Inject
constructor(private val playerDao: PlayerDao) : PlayerDataSource {

    override fun addPlayer(player: Player): Completable {
        return Completable.create {
            try{
                playerDao.addPlayer(player)
                it.onComplete()
            }catch (ex: Throwable){
                it.onError(ex)
            }
        }
    }

    override fun updatePlayer(player: Player): Completable {
        return Completable.create {
            try{
                playerDao.updatePlayer(player)
                it.onComplete()
            }catch (ex: Throwable){
                it.onError(ex)
            }
        }
    }

    override fun getPlayer(playerName: String): Single<Player> {
        return playerDao.getPlayer(playerName)
                .take(1)
                .firstOrError()
                .flatMap { players -> return@flatMap Single.just(players[0]) }
    }

    override fun getCurrentPlayer(playerName: String): Flowable<Player> {
        return playerDao.getPlayer(playerName)
                .flatMap{ players -> return@flatMap Flowable.just(players[0])}
    }
}