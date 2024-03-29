package com.github.kiolk.alphabet.presentation.splash

import android.content.SharedPreferences
import com.arellomobile.mvp.InjectViewState
import com.github.kiolk.alphabet.data.domain.levels.ConfigureLevelsUseCase
import com.github.kiolk.alphabet.data.domain.settings.InitSettingsUseCase
import com.github.kiolk.alphabet.data.domain.words.InitGameDatabase
import com.github.kiolk.alphabet.data.domain.words.InitGameUseCase
import com.github.kiolk.alphabet.data.models.game.GameSettings
import com.github.kiolk.alphabet.data.models.word.Word
import com.github.kiolk.alphabet.data.source.settings.SettingsRepository
import com.github.kiolk.alphabet.data.source.words.WordsRepository
import com.github.kiolk.alphabet.presentation.base.BasePresenter
import com.github.kiolk.alphabet.utils.Constants.MAX_WORDS_IN_GAME
import com.github.kiolk.alphabet.utils.Constants.MIN_WORDS_IN_GAME
import com.github.kiolk.alphabet.utils.Data
import com.github.kiolk.alphabet.utils.RxSchedulerProvider
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class SplashPresenter
@Inject
constructor(
    private val initGameUseCase: InitGameUseCase,
    private val rxSchedulerProvider: RxSchedulerProvider,
    private val wordsRepository: WordsRepository,
    private val settingsRepository: SettingsRepository,
    private val sharedPreferences: SharedPreferences,
    private val configureLevelsUseCase: ConfigureLevelsUseCase,
    private val initSettingsUseCase: InitSettingsUseCase,
    private val initDataBaseUseCase: InitGameDatabase
) : BasePresenter<SplashView>() {

    private var counter = 0
    private lateinit var allSettings: MutableList<GameSettings>
    private lateinit var availableSettings: MutableList<GameSettings>

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        val isInited = sharedPreferences.getBoolean(KEY_INITED, false)

        if (isInited) {
            configuerLevels()
        } else {
            addDisposable(
                initGameUseCase.execute(InitGameUseCase.Params())
                    .compose(rxSchedulerProvider.goIoToMainTransformerFloweable())
                    .subscribe(this::words, this::initSettingError)
            )
        }
    }

    private fun initSettingError(throwable: Throwable) {
        Timber.e(throwable)
    }

    private fun words(words: List<Word>) {
        addDisposable(
            initDataBaseUseCase.execute(InitGameDatabase.Params(words))
                .compose(rxSchedulerProvider.goIoToMainTransformerComplitable())
                .subscribe(this::initSettings, this::initSettingError)
        )
    }

    private fun initSettings() {
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
        if (words.size >= MIN_WORDS_IN_GAME) {
            val needAddSettings = allSettings[counter]

            if (availableSettings.size != 0 && needAddSettings.gameSchema.letterValue != availableSettings[availableSettings.size - 1].gameSchema.letterValue) {
                needAddSettings.isAvailable = true
            } else if (availableSettings.size == 0) {
                needAddSettings.isAvailable = true
            }

            needAddSettings.numberAskedWords = setAsckedWords(words.size)

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
        addDisposable(initSettingsUseCase.execute(InitSettingsUseCase.Params(availableSettings))
                .compose(rxSchedulerProvider.goIoToMainTransformerComplitable())
                .subscribe(this::configuerLevels))
    }

    private fun setAsckedWords(total: Int): Int{
        if(total < MAX_WORDS_IN_GAME){
            return MIN_WORDS_IN_GAME
        }else{
            return Math.min((total * 0.5f).toInt(), MAX_WORDS_IN_GAME)
        }
    }

    private fun configuerLevels(){
        addDisposable(configureLevelsUseCase.execute(ConfigureLevelsUseCase.Params())
                .compose(rxSchedulerProvider.goIoToMainTransformerComplitable())
                .subscribe(this::openMainScreen))
    }

    private fun openMainScreen() {
        sharedPreferences.edit().putBoolean(KEY_INITED, true).apply()
        viewState.openMainScreen()
    }

    companion object {
        private val KEY_INITED = "KEY_INITED"
    }
}