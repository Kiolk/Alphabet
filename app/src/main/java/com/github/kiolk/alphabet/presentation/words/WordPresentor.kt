package com.github.kiolk.alphabet.presentation.words

import android.content.Context
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.data.domain.PrepareGameSetUseCase
import com.github.kiolk.alphabet.data.domain.UpdateWordsFromFile
import com.github.kiolk.alphabet.data.domain.player.GetCurrentLevelUseCase
import com.github.kiolk.alphabet.data.domain.topics.GetActualTopicUseCase
import com.github.kiolk.alphabet.data.domain.topics.PrepareTopicUseCase
import com.github.kiolk.alphabet.data.domain.words.GetAlphabetUseCase
import com.github.kiolk.alphabet.data.domain.words.PrepareGameUseCase
import com.github.kiolk.alphabet.data.models.game.GameResult
import com.github.kiolk.alphabet.data.models.game.GameSettings
import com.github.kiolk.alphabet.data.models.letter.Letter
import com.github.kiolk.alphabet.data.models.level.Level
import com.github.kiolk.alphabet.data.models.topic.Topic
import com.github.kiolk.alphabet.data.models.word.Word
import com.github.kiolk.alphabet.data.source.settings.SettingsRepository
import com.github.kiolk.alphabet.data.source.words.WordsRepository
import com.github.kiolk.alphabet.presentation.base.BasePresenter
import com.github.kiolk.alphabet.utils.Data
import com.github.kiolk.alphabet.utils.RxSchedulerProvider
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class WordPresentor
@Inject
constructor(private val context: Context,
            private val rxSchedulerProvider: RxSchedulerProvider,
            private val repository: WordsRepository,
            private val settingsRepository: SettingsRepository,
            private val updateWordsFromFile: UpdateWordsFromFile,
            private val getAlphabetUseCase: GetAlphabetUseCase,
            private val getActualTopicUseCase: GetActualTopicUseCase,
            private val prepareGameUseCase: PrepareGameUseCase,
            private val prepareTopicUseCase: PrepareTopicUseCase) : BasePresenter<WordsView>() {

    private var isMainScreenOpened: Boolean = true

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initAlphabet()

        addDisposable(getAlphabetUseCase.execute(GetAlphabetUseCase.Params())
                .compose(rxSchedulerProvider.goIoToMainTransformerFloweable())
                .subscribe(this::setAlphabet))

        showWordsTopic()
    }

    fun onLetterSelected(letter: Letter) {
        isMainScreenOpened = false
        viewState.setSelectedLetter(letter)
        addDisposable(settingsRepository.getSettingsByLatter(letter)
                .compose(rxSchedulerProvider.goIoToMainTransformerFloweable())
                .subscribe(this::getSelectedSettings, {}))
    }

    fun getSelectedSettings(settings: List<GameSettings>) {
        viewState.setAvailableTopics(settings)
    }

    private fun setAlphabet(alphabet: List<Letter>) {
        viewState.setAlphabet(Data.alphabet)
    }

    private fun setTopics(topics: List<Topic>) {
        viewState.setWordTopics(topics)
    }

    private fun setAvailablTopics(topics: List<GameSettings>) {
        viewState.setAvailableTopics(topics)
    }

    fun updateWords(pathForFile: String) {
        addDisposable(updateWordsFromFile.execute(UpdateWordsFromFile.Params(pathForFile)).compose(rxSchedulerProvider.goIoToMainTransformerComplitable())
                .subscribe({ Log.d("MyLogs", "Succes") }, { Log.d("MyLogs", "Error$it") }))
    }

    fun onBackPressed() {
        if (isMainScreenOpened) {
            viewState.endGame()
        } else {
            viewState.showMain()
            isMainScreenOpened = true
        }
    }

    fun showWordsTopic() {
        addDisposable(getActualTopicUseCase.execute(GetActualTopicUseCase.Params())
                .take(1)
                .compose(rxSchedulerProvider.goIoToMainTransformerFloweable())
                .subscribe(this::setTopics))
    }

    fun onTopicClick(gameSettings: GameSettings) {
        addDisposable(prepareGameUseCase.execute(PrepareGameUseCase.Params(gameSettings))
                .compose(rxSchedulerProvider.getIoToMainTransformerSingle())
                .subscribe(this::onWordSuccess, Timber::e))
    }

    private fun onWordSuccess(gameResult: GameResult) {
        viewState.onStartTopic(gameResult)
    }

    fun onWordsTopicClick(topic: Topic) {
        addDisposable(prepareTopicUseCase.execute(PrepareTopicUseCase.Params(topic))
                .compose(rxSchedulerProvider.getIoToMainTransformerSingle())
                .subscribe(this::onWordSuccess, Timber::e))
    }
}
