package com.github.kiolk.alphabet.presentation.game.game

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.OnClick
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.data.models.game.GameResult
import com.github.kiolk.alphabet.data.models.game.GameSettings
import com.github.kiolk.alphabet.data.models.word.Word
import com.github.kiolk.alphabet.di.modules.presenter.GamePresenterModule
import com.github.kiolk.alphabet.presentation.base.controller.BaseController
import com.github.kiolk.alphabet.presentation.common.CharactersLayout
import com.github.kiolk.alphabet.presentation.words.adapter.SelectPhotoAdapter
import com.github.kiolk.alphabet.presentation.words.adapter.SelectPhotoDecorator
import com.github.kiolk.alphabet.utils.BlurBuilder
import com.github.kiolk.alphabet.utils.BundleBuilder

class GameController : BaseController, GameView {

    constructor(gameSettings: GameSettings) : super(BundleBuilder(Bundle())
            .setParseleable(BUNDLE_GAME_SETTINGS, gameSettings).build())

    constructor(args: Bundle) : super(args)

    @BindView(R.id.chars_layout)
    lateinit var charsLayout: CharactersLayout

    @BindView(R.id.rw_words_photos)
    lateinit var wordsPhotots: RecyclerView

    @BindView(R.id.v_game_bluer_holder)
    lateinit var vBlure : ImageView

    @BindView(R.id.btn_word_screen_next_word)
    lateinit var btnOnNext: Button

    @BindView(R.id.iv_game_tap_button)
    lateinit var ivTap : ImageButton

    @BindView(R.id.iv_word_blur)
    lateinit var ivWordBlur : ImageView

    @BindView(R.id.tv_game_read_word)
    lateinit var tvWord : TextView

    @InjectPresenter
    lateinit var presenter: GamePresenter

    lateinit var adapter: SelectPhotoAdapter

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup): View {
        return inflater.inflate(R.layout.controller_game, container, false)
    }

    override fun onViewBind(itemVie: View) {
        super.onViewBind(itemVie)
        val layout = GridLayoutManager(this.activity?.baseContext, 2, GridLayoutManager.HORIZONTAL, false)
        adapter = SelectPhotoAdapter(itemVie.context, emptyList()) { word ->
            presenter.onCheckAnswer(word)
        }
        wordsPhotots.layoutManager = layout
        wordsPhotots.addItemDecoration(SelectPhotoDecorator(5))
        wordsPhotots.adapter = adapter
    }

    override fun setWordPictures(list: List<Word>) {
        adapter.setItems(list)
    }

    override fun setAnswer(userAnswer: Word, correct: Word) {
        adapter.setCorrectAnswer(userAnswer, correct)
    }

    override fun enableNextButton() {
        btnOnNext.isEnabled = true
    }

    override fun disableNext() {
        btnOnNext.isEnabled = false
    }

    override fun setWord(word: String) {
//        charsLayout.setWord(word)
        tvWord.text = word
    }

    override fun showResult(resullt: GameResult) {
//        router.pushController()
    }

    override fun showBlurHolder() {
        vBlure.setImageBitmap(activity?.baseContext?.let { BlurBuilder.blur(it, wordsPhotots) })
        vBlure.visibility = View.VISIBLE
    }

    override fun hideBlurHolder() {
        vBlure.visibility = View.GONE
        adapter.isEnableSelected = false
    }

    @OnClick(R.id.iv_game_tap_button)
    fun onTapClick(){
        presenter.onTapClick()
    }

    override fun showTapButton() {
        ivTap.visibility = View.VISIBLE
    }

    override fun hideTapButton() {
        ivTap.visibility = View.GONE
        }



    override fun showWord() {
        ivWordBlur.visibility = View.GONE
    }

    override fun hideWord() {
        ivWordBlur.setImageBitmap(activity?.baseContext?.let { BlurBuilder.blur(it, tvWord) })
        ivWordBlur.visibility = View.VISIBLE
    }

    @OnClick(R.id.tv_game_read_word)
    fun onWordClick(){
        presenter.onWordClick()
    }

    @OnClick(R.id.btn_word_screen_next_word)
    fun onNextClick() {
        presenter.onNextWordPress()
    }

    @ProvidePresenter
    fun providePresenter(): GamePresenter {
        return getApplicationComponent()
                .plusGamePresenter(GamePresenterModule(args.getParcelable(BUNDLE_GAME_SETTINGS)
                        ?: GameSettings("", "", "", "", "", 0)))
                .presenter
    }

    companion object {
        const val BUNDLE_GAME_SETTINGS: String = "BUNDLE_GAME_SETTINGS"
    }
}