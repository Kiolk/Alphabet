package com.github.kiolk.alphabet.data.source.player.local

import android.arch.persistence.room.*
import com.github.kiolk.alphabet.data.models.player.Player
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface PlayerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPlayer(player: Player)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updatePlayer(player: Player)

    @Query("SELECT * FROM player WHERE player_name LIKE :playerName")
    fun getPlayer(playerName: String): Flowable<List<Player>>
}