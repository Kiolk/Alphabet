package com.github.kiolk.alphabet.presentation.splash

import com.arellomobile.mvp.InjectViewState
import com.github.kiolk.alphabet.data.domain.words.InitGameUseCase
import com.github.kiolk.alphabet.data.models.game.GameSettings
import com.github.kiolk.alphabet.data.models.word.Word
import com.github.kiolk.alphabet.data.source.settings.SettingsRepository
import com.github.kiolk.alphabet.data.source.words.WordsRepository
import com.github.kiolk.alphabet.presentation.base.BasePresenter
import com.github.kiolk.alphabet.utils.Data
import com.github.kiolk.alphabet.utils.RxSchedulerProvider
import javax.inject.Inject

@InjectViewState
class SplashPresenter
@Inject
constructor(private val initGameUseCase: InitGameUseCase,
            private val rxSchedulerProvider: RxSchedulerProvider,
            private val wordsRepository: WordsRepository,
            private val settingsRepository: SettingsRepository) : BasePresenter<SplashView>() {

    private var counter = 0
    private lateinit var allSettings: MutableList<GameSettings>
    private lateinit var availableSettings: MutableList<GameSettings>

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        addDisposable(initGameUseCase.execute(InitGameUseCase.Params())
                .compose(rxSchedulerProvider.goIoToMainTransformerComplitable())
                .subscribe(this::initSettings))
    }

    fun initSettings() {
        allSettings = mutableListOf()
        availableSettings = mutableListOf()
        for (letter in Data.alphabet) {
            Data.gameSettingsPatterns.forEach { pattern ->
                val tmp = pattern
                tmp.setLetter(letter.letterValue)
                allSettings.add(tmp.build())
            }
        }

        checkSettings(allSettings[counter])
    }

    fun checkSettings(setting: GameSettings) {
        addDisposable(wordsRepository.isSettingsAvailable(setting)
                .compose(rxSchedulerProvider.goIoToMainTransformerFloweable())
                .subscribe(this::availableWords))
    }

    fun availableWords(words: List<Word>) {
        if (words.size > 3) {
            val needAddSettings = allSettings[counter]

            if(availableSettings.size != 0 && needAddSettings.gameSchema.letterValue != availableSettings[availableSettings.size -1].gameSchema.letterValue){
                needAddSettings.isAvailable = true
            }else if(availableSettings.size == 0){
                needAddSettings.isAvailable = true
            }

            availableSettings.add(needAddSettings)
        }

        ++counter

        if (counter == allSettings.size) {
            setSettings()
        } else {
            checkSettings(allSettings[counter])
        }
    }

    fun setSettings() {
        addDisposable(settingsRepository.setSettings(availableSettings)
                .compose(rxSchedulerProvider.goIoToMainTransformerComplitable())
                .subscribe({viewState.openMainScreen()}))
    }
}