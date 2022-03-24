package com.github.kiolk.alphabet.data.source.player.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.github.kiolk.alphabet.data.models.player.Player
import io.reactivex.Flowable

@Dao
interface PlayerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPlayer(player: Player)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updatePlayer(player: Player)

    @Query("SELECT * FROM player WHERE player_name LIKE :playerName")
    fun getPlayer(playerName: String): Flowable<List<Player>>
}