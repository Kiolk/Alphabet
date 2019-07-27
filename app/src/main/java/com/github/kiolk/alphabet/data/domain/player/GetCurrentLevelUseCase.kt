package com.github.kiolk.alphabet.data.domain.player

import com.github.kiolk.alphabet.data.domain.UseCase
import com.github.kiolk.alphabet.data.models.level.Level
import com.github.kiolk.alphabet.data.models.level.LevelTypes
import com.github.kiolk.alphabet.data.models.level.LevelViewModel
import com.github.kiolk.alphabet.data.source.player.PlayerRepository
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class GetCurrentLevelUseCase
@Inject
constructor(private val playerRepository: PlayerRepository) : UseCase<Flowable<LevelViewModel>, GetCurrentLevelUseCase.Params>{

   override fun execute(params: Params): Flowable<LevelViewModel> {
      return playerRepository.getPlayer("Main")
              .flatMapPublisher{player ->
                 val current = LevelTypes.getLevel(player.stars)
                 val next = LevelTypes.getNextLevel(player.stars)

                 return@flatMapPublisher Flowable.just(LevelViewModel(player.stars, current.needStars, next?.needStars, current.level))}
   }

   class Params
}