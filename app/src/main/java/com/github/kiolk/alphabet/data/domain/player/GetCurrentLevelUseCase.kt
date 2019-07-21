package com.github.kiolk.alphabet.data.domain.player

import com.github.kiolk.alphabet.data.domain.UseCase
import com.github.kiolk.alphabet.data.models.level.Level
import com.github.kiolk.alphabet.data.models.level.LevelTypes
import com.github.kiolk.alphabet.data.source.player.PlayerRepository
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class GetCurrentLevelUseCase
@Inject
constructor(private val playerRepository: PlayerRepository) : UseCase<Flowable<Level>, GetCurrentLevelUseCase.Params>{

   override fun execute(params: Params): Flowable<Level> {
      return playerRepository.getPlayer("Main")
              .flatMapPublisher{player -> Flowable.just(LevelTypes.getLevel(player.stars))}
   }

   class Params
}