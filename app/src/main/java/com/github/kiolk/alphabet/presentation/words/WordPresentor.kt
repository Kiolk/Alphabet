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
    private var isSyllable = false
    private var isSentence = false
    private lateinit var currentSet: MutableList<String>

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
//        repository.setWordsSet(listOf(Words("words", context.resources.getDrawable(R.drawable.slova).toString(), words.toList())))
//                .compose(rxSchedulerProvider.goIoToMainTransformerComplitable())
//                .subscribe(this::onSuccess, this::onError)

        addDisposable(repository.setWordList(testSetOfWord)
                .compose(rxSchedulerProvider.goIoToMainTransformerComplitable())
                .subscribe(this::onSuccess, this::onError))

        addDisposable(settingsRepository.setSettings(testSettings)
                .compose(rxSchedulerProvider.goIoToMainTransformerComplitable())
                .subscribe({}, {}))

        addDisposable(settingsRepository.getAllSettings()
                .compose(rxSchedulerProvider.goIoToMainTransformerFloweable())
                .subscribe({ settings ->
                    currentSet = words.toMutableList()
                    workSet = currentSet
                    onNextWordPress()
                    viewState.setAvailableTopics(settings)
//        viewState.setWordPictures(mockObject)
//        viewState.setWord(mockObject[0].value)
                    viewState.disableNext()
                }, {}))

//        currentSet = words.toMutableList()
//        workSet = currentSet
//        onNextWordPress()
//        viewState.setAvailableTopics(WordsSet.values().map { Topic(it.resId, it.title, it.query, true) })
////        viewState.setWordPictures(mockObject)
////        viewState.setWord(mockObject[0].value)
//        viewState.disableNext()
    }

    fun onCheckAnswer(word: Word) {
//        viewState.setAnswer(word, mockObject[1])
        viewState.enableNextButton()
    }

    private fun onSettingsUpdateSucess(){

    }

    private fun onSettingsUpdateError(){}

    private fun onSuccess() {
        onNextWordPress()
    viewState.setAlphabet(alphabet)
    }

    private fun onError(throwable: Throwable) {
        onNextWordPress()
    }

    override fun attachView(view: WordsView?) {
        super.attachView(view)
        viewState.setSentanceEnable(isSentence)
        viewState.setSyllableEnable(isSyllable)
    }

    fun onNextWordPress() {
//        val random = Random().nextInt(workSet.size)
//        viewState.setWord(workSet[random])
//        viewState.setWord(mockObject2[0].value)
//        viewState.setWordPictures(mockObject)
        viewState.disableNext()
    }

    fun onSyllableChanged(enable: Boolean) {
        isSyllable = enable
        prepareDisplays()
    }

    fun onSentenceChanged(enable: Boolean) {
        isSentence = enable
        prepareDisplays()
    }

    private fun prepareDisplays() {
        workSet = currentSet

        if (!isSentence) {
            val list = mutableListOf<String>()
            workSet.forEachIndexed() { int, s ->
                list.addAll(s.split(" "))
                list.forEach { it.toLowerCase() }
            }
            workSet = list
        }

        if (isSyllable) {
            workSet.forEachIndexed() { int, s ->
                workSet[int] = s.replace("-", "")
            }
        }
        onNextWordPress()
    }

    fun setTopic(topic: Topic) {
        if (topic.title == WordsSet.SHORT_WORDS.title) {
            currentSet = words.toMutableList()
        } else if (topic.title == WordsSet.SYLLABLE.title) {
            currentSet = syllables.toMutableList()
        } else if (topic.title == WordsSet.THREE_LATERS_WORDS.title) {
            currentSet = threeLattersWord.toMutableList()
        } else if (topic.title == WordsSet.FOR_CHAR_WORDS.title) {
            currentSet = forCharWords.toMutableList()
        } else if (topic.title == WordsSet.FOUR_ONE_VOWEL.title) {
            currentSet = oneFrom.toMutableList()
        } else if (topic.title == WordsSet.FIVE_CHAR.title) {
            currentSet = fiveChar.toMutableList()
        } else if (topic.title == WordsSet.SIX_CHAR.title) {
            currentSet = sixChar.toMutableList()
        }
        prepareDisplays()
        viewState.closeMenu()
    }

    fun onLetterSelected(letter: Letter) {
        Log.d("MyLogs", "${letter.letter}")
        viewState.setSelectedLetter(letter)
    }

    fun updateWords(pathForFile: String) {
       addDisposable(updateWordsFromFile.execute(UpdateWordsFromFile.Params(pathForFile)).
                compose(rxSchedulerProvider.goIoToMainTransformerComplitable())
                .subscribe({Log.d("MyLogs", "Succes")}, {Log.d("MyLogs", "Error$it")}))
    }

    companion object {
        //        val mockObject = listOf<Word>(Word("cok", "https://picsum.photos/id/2/200"),
//                Word("cok1", "https://picsum.photos/id/1/200"),
//                Word("cok2", "https://picsum.photos/id/3/200"),
//                Word("cok3", "https://picsum.photos/id/4/200"))
//
//        val mockObject2 = listOf<Word>(Word("cok4", "https://picsum.photos/id/5/200"),
//                Word("cok5", "https://picsum.photos/id/6/200"),
//                Word("cok6", "https://picsum.photos/id/7200"),
//                Word("cok7", "https://picsum.photos/id/8/200"))
        val alphabet = listOf(Letter("а", "а", "d"),
                Letter("б", "б", "d"),
                Letter("в", "в", "d"),
                Letter("г", "г", "d"),
                Letter("д", "д", "d"),
                Letter("дж", "дж", "d"),
                Letter("дз", "дз", "d"),
                Letter("е", "е", "d"),
                Letter("ё", "ё", "d"),
                Letter("ж", "ж", "d"),
                Letter("з", "з", "d"),
                Letter("і", "і", "d"),
                Letter("й", "й", "d"),
                Letter("к", "к", "d")
        )

        val testSetOfWord = listOf<Word>(
                Word("жук", "жук", "https://upload.wikimedia.org/wikipedia/commons/0/0e/Dynastes_hercules_ecuatorianus_MHNT.jpg", listOf("жывёлы")),
                Word("мак", "мак", "https://upload.wikimedia.org/wikipedia/commons/7/71/Poppies_in_the_Sunset_on_Lake_Geneva.jpg", listOf("расліны")),

                Word("вада", "ва-да", "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a6/Waterdrops_%284648726722%29.jpg/800px-Waterdrops_%284648726722%29.jpg", listOf("рэчыва")),
                Word("тыгр", "тыгр", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/da/Panthera_tigris_tigris_Tidoba_20150306.jpg/1280px-Panthera_tigris_tigris_Tidoba_20150306.jpg", listOf("рэчыва")),
                Word("слон", "слон", "https://upload.wikimedia.org/wikipedia/commons/3/37/African_Bush_Elephant.jpg", listOf("жывёлы")),
                Word("снег", "снег", "https://upload.wikimedia.org/wikipedia/commons/e/ed/Sparkling-snow.crystals.jpg", listOf("жывёлы")),
                Word("кола", "ко-ла", "https://upload.wikimedia.org/wikipedia/commons/3/32/Tekerlek.jpg", listOf("рэчыва")),
                Word("мука", "му-ка", "https://upload.wikimedia.org/wikipedia/commons/6/64/All-Purpose_Flour_%284107895947%29.jpg", listOf("рэчыва")),
                Word("куля", "ку-ля", "https://upload.wikimedia.org/wikipedia/commons/6/65/Halvmantlad.jpg", listOf("рэчыва")),
                Word("рука", "ру-ка", "https://upload.wikimedia.org/wikipedia/commons/b/bf/Handskelett.png", listOf("рэчыва")),
                Word("цень", "цень", "https://upload.wikimedia.org/wikipedia/commons/3/33/Looking_down_from_The_Eiffel_Tower%2C_Paris_8_April_2007.jpg", listOf("рэчыва")),
                Word("рыба", "ры-ба", "https://upload.wikimedia.org/wikipedia/commons/2/23/Georgia_Aquarium_-_Giant_Grouper_edit.jpg", listOf("жывёлы")),

                Word("чабор", "ча-бор", "https://upload.wikimedia.org/wikipedia/commons/e/ee/%D0%9A%D0%B2%D0%B5%D1%82%D0%BA%D1%96_%D1%87%D0%B0%D0%B1%D0%BE%D1%80%D0%B0.jpg", listOf("расліны")),
                Word("жолуд", "жо-луд", "https://upload.wikimedia.org/wikipedia/commons/d/d8/Acorns_small_to_large.jpg", listOf("рэчыва")),
                Word("месяц", "ме-сяц", "https://upload.wikimedia.org/wikipedia/commons/e/e1/FullMoon2010.jpg", listOf("рэчыва")),
                Word("човен", "чо-вен", "https://upload.wikimedia.org/wikipedia/commons/5/50/Do%C5%82banka.jpg", listOf("рэчыва")),
                Word("чарот", "ча-рот", "https://upload.wikimedia.org/wikipedia/commons/1/1d/ScirpusSylvaticus2.jpg", listOf("расліны")),
                Word("вецер", "ве-цер", "https://upload.wikimedia.org/wikipedia/commons/e/e2/Cherry_tree_moving_in_the_wind_1.gif", listOf("рэчыва")),
                Word("чмель", "чмель", "https://upload.wikimedia.org/wikipedia/commons/4/49/Bumblebee_heuchera.jpg", listOf("жывёлы")),

                Word("малако", "ма-ла-ко", "https://upload.wikimedia.org/wikipedia/commons/0/0e/Milk_glass.jpg", listOf("рэчыва")),
                Word("чарвяк", "чар-вяк", "https://upload.wikimedia.org/wikipedia/commons/3/30/Regenwurm1.jpg", listOf("жывёлы")),
                Word("бульба", "буль-ба", "https://upload.wikimedia.org/wikipedia/commons/0/09/Bamberger_Hoernle.jpg", listOf("рэчыва")),
                Word("камень", "ка-мень", "https://upload.wikimedia.org/wikipedia/commons/a/aa/GabbroRockCreek1.jpg", listOf("рэчыва")),

                Word("чаравік", "ча-ра-вік", "https://upload.wikimedia.org/wikipedia/commons/c/cc/S3_safety_footwear.jpg", listOf("рэчыва")),
                Word("лес", "лес", "https://upload.wikimedia.org/wikipedia/commons/a/a0/Poland_Bialowieza_-_BPN.jpg", listOf("прырода")),
                Word("нос", "нос", "https://upload.wikimedia.org/wikipedia/commons/d/d0/Canine-nose.jpg", listOf("цела")),
                Word("бэз", "бэз", "https://upload.wikimedia.org/wikipedia/commons/a/ad/Syringa_%27Pamyat_o_Vekhove%27_01.jpg", listOf("кветкі, расліны")),
                Word("дым", "дым", "https://upload.wikimedia.org/wikipedia/commons/f/f3/Ognisko_ubt_0126.jpeg", listOf("рэчыва")),
                Word("чалавек", "ча-ла-век", "https://upload.wikimedia.org/wikipedia/commons/2/22/Da_Vinci_Vitruve_Luc_Viatour.jpg", listOf("")),
                Word("рамонак", "ра-мо-нак", "https://upload.wikimedia.org/wikipedia/commons/0/07/Matricaria_recutita_2008_07_06.JPG", listOf("кветкі")),
                Word("парасон", "па-ра-сон", "https://upload.wikimedia.org/wikipedia/commons/d/d4/Gustave_Caillebotte_-_Jour_de_pluie_%C3%A0_Paris.jpg", listOf("")),
                Word("ракета", "ра-ке-та", "https://upload.wikimedia.org/wikipedia/commons/8/8b/Proton-K-Zarya.jpg", listOf("")),
                Word("капялюш", "ка-пя-люш", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5d/Pierre_Auguste_Renoir_-_Woman_in_a_Flowered_Hat_-_Google_Art_Project.jpg/800px-Pierre_Auguste_Renoir_-_Woman_in_a_Flowered_Hat_-_Google_Art_Project.jpg", listOf("")),

                Word("ноль", "ноль", "https://www.vectorportal.com/img_novi/flat-calculator.jpg", listOf("лічбы")),
                Word("адзін", "а-дзін", "https://www.vectorportal.com/img_novi/pete1_6859.jpg", listOf("лічбы")),
                Word("два", "два", "https://www.vectorportal.com/img_novi/hdpe2_6851.jpg", listOf("лічбы")),
                Word("тры", "тры", "https://www.vectorportal.com/img_novi/pvc3_6862.jpg", listOf("лічбы")),
                Word("чатыры", "ча-ты-ры", "https://www.vectorportal.com/img_novi/ldpe4_6852.jpg", listOf("лічбы")),
                Word("пяць", "пяць", "https://www.vectorportal.com/img_novi/recyclable5a_6863.jpg", listOf("лічбы")),
                Word("шэсць", "шэсць", "https://www.vectorportal.com/img_novi/ps6_6861.jpg", listOf("лічбы")),
                Word("сем", "сем", "https://www.vectorportal.com/StockVectors/Symbols-and-Signs/VECTOR-SYMBOL-FOR-OTHER-7/6853.aspx", listOf("лічбы")),
                Word("восем", "во-сем", "https://www.vectorportal.com/img_novi/snooker-ball-vector_8432.jpg", listOf("лічбы")),
                Word("дзевяць", "дзе-вяць", "https://image.freepik.com/free-photo/hand-holding-number-nine-sign_53876-47259.jpg", listOf("лічбы")),
                Word("дзесяць", "дзе-сяць", "https://www.vectorportal.com/img_novi/step-descent.jpg", listOf("лічбы"))
                )

            val testSettings = listOf<GameSettings>(
                    GameSettings("Тры", "https://upload.wikimedia.org/wikipedia/be/6/68/Try_%C4%8Darapachi_%28%D0%B2%D0%BE%D0%BA%D0%BB%D0%B0%D0%B4%D0%BA%D0%B0_%D0%B0%D0%BB%D1%8C%D0%B1%D0%BE%D0%BC%D0%B0%29.jpg", "___", "%", "%", 4),
                    GameSettings("Чатыры", "https://upload.wikimedia.org/wikipedia/en/4/42/Beatles_-_Abbey_Road.jpg", "____", "%", "%", 4),
                    GameSettings("Пяць", "https://upload.wikimedia.org/wikipedia/en/4/42/Beatles_-_Abbey_Road.jpg", "_____", "%", "%", 4),
                    GameSettings("Шэсць", "https://upload.wikimedia.org/wikipedia/en/4/42/Beatles_-_Abbey_Road.jpg", "______", "%", "%", 4)
            )

    }
}
