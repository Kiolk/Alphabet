package com.github.kiolk.alphabet.presentation.words

import android.content.Context
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.github.kiolk.alphabet.data.domain.UpdateWordsFromFile
import com.github.kiolk.alphabet.data.models.game.GameSettings
import com.github.kiolk.alphabet.data.models.letter.Letter
import com.github.kiolk.alphabet.data.models.word.Word
import com.github.kiolk.alphabet.data.source.settings.SettingsRepository
import com.github.kiolk.alphabet.data.source.words.WordsRepository
import com.github.kiolk.alphabet.di.modules.AppModule.Companion.FIVE_CHAR
import com.github.kiolk.alphabet.di.modules.AppModule.Companion.FOR_CHAR_WORDS
import com.github.kiolk.alphabet.di.modules.AppModule.Companion.ONE_FROM
import com.github.kiolk.alphabet.di.modules.AppModule.Companion.SIX_CHAR
import com.github.kiolk.alphabet.di.modules.AppModule.Companion.SYLLABLES_TAG
import com.github.kiolk.alphabet.di.modules.AppModule.Companion.THREE_LATTERS_WORD
import com.github.kiolk.alphabet.di.modules.AppModule.Companion.WORDS_TAG
import com.github.kiolk.alphabet.presentation.WordsSet
import com.github.kiolk.alphabet.presentation.adapters.Topic
import com.github.kiolk.alphabet.presentation.base.BasePresenter
import com.github.kiolk.alphabet.utils.Data.alphabet
import com.github.kiolk.alphabet.utils.RxSchedulerProvider
import javax.inject.Inject
import javax.inject.Named

@InjectViewState
class WordPresentor
@Inject
constructor(private val context: Context,
            @Named(WORDS_TAG) var words: Array<String>,
            @Named(THREE_LATTERS_WORD) var threeLattersWord: Array<String>,
            @Named(FOR_CHAR_WORDS) var forCharWords: Array<String>,
            @Named(SYLLABLES_TAG) var syllables: Array<String>,
            @Named(ONE_FROM) var oneFrom: Array<String>,
            @Named(FIVE_CHAR) var fiveChar: Array<String>,
            @Named(SIX_CHAR) var sixChar: Array<String>,
            private val rxSchedulerProvider: RxSchedulerProvider,
            private val repository: WordsRepository,
            private val settingsRepository: SettingsRepository,
            private val updateWordsFromFile: UpdateWordsFromFile) : BasePresenter<WordsView>() {

    private lateinit var workSet: MutableList<String>
    private lateinit var currentSet: MutableList<String>

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        addDisposable(settingsRepository.getAllSettings()
                .take(1)
                .compose(rxSchedulerProvider.goIoToMainTransformerFloweable())
                .subscribe({ settings ->
                    currentSet = words.toMutableList()
                    workSet = currentSet
                    viewState.setAvailableTopics(settings)
                    viewState.setAlphabet(alphabet)
                }, {}))
    }

    fun onLetterSelected(letter: Letter) {
        viewState.setSelectedLetter(letter)
        addDisposable(settingsRepository.getSettingsByLatter(letter)
                .compose(rxSchedulerProvider.goIoToMainTransformerFloweable())
                .subscribe(this::getSelectedSettings,{}))
    }

    fun getSelectedSettings(settings: List<GameSettings>){
        viewState.setAvailableTopics(settings)
    }

    fun updateWords(pathForFile: String) {
       addDisposable(updateWordsFromFile.execute(UpdateWordsFromFile.Params(pathForFile)).
                compose(rxSchedulerProvider.goIoToMainTransformerComplitable())
                .subscribe({Log.d("MyLogs", "Succes")}, {Log.d("MyLogs", "Error$it")}))
    }
}
