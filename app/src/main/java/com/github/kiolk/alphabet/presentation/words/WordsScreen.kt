package com.github.kiolk.alphabet.presentation.words

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Button
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bluelinelabs.conductor.ChangeHandlerFrameLayout
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.VerticalChangeHandler
import com.github.kiolk.alphabet.App
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.data.models.game.GameSettings
import com.github.kiolk.alphabet.data.models.letter.Letter
import com.github.kiolk.alphabet.data.models.word.Word
import com.github.kiolk.alphabet.presentation.base.BaseView
import com.github.kiolk.alphabet.presentation.common.CharactersLayout
import com.github.kiolk.alphabet.presentation.game.game.GameController
import com.github.kiolk.alphabet.presentation.game.preview.GamePreviewController
import com.github.kiolk.alphabet.presentation.home.HomeController
import com.github.kiolk.alphabet.presentation.words.adapter.SelectPhotoAdapter
import com.github.kiolk.alphabet.presentation.words.adapter.SelectPhotoDecorator
import com.github.kiolk.alphabet.utils.CsvParser
import com.github.kiolk.alphabet.utils.toPx
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu

class WordsScreen : MvpAppCompatActivity(), WordsView, BaseView, MenuListenerView {

    @InjectPresenter
    lateinit var presenter: WordPresentor

    @BindView(R.id.general_controller_container)
    lateinit var controllerContainer: ChangeHandlerFrameLayout

    lateinit var router: Router

    lateinit var menuLeft: SlidingMenu

    lateinit var leftMenu: LeftMenuFragment

    lateinit var rightMenu: RightMenuFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        leftMenu = LeftMenuFragment()
        rightMenu = RightMenuFragment()

        menuLeft = SlidingMenu(this)
        menuLeft.mode = SlidingMenu.LEFT_RIGHT
        menuLeft.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN or SlidingMenu.TOUCHMODE_FULLSCREEN)
        menuLeft.behindOffset = 60.toPx
        menuLeft.setFadeDegree(0.35f)
        menuLeft.attachToActivity(this, SlidingMenu.SLIDING_CONTENT)
        menuLeft.setMenu(R.layout.right_menu_layout)
        supportFragmentManager.beginTransaction().replace(R.id.left_menu_container, leftMenu).commit()
        menuLeft.setSecondaryMenu(R.layout.left_menu_layout)
        supportFragmentManager.beginTransaction().replace(R.id.right_menu_container, rightMenu).commit()

        router = Conductor.attachRouter(this, findViewById(R.id.general_controller_container), savedInstanceState)
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(HomeController(Letter("A", "F", "d"))))
        }
    }

    override fun showProgress() {
    }

    override fun hideProgress() {
    }

    override fun onBackPressed() {
        if (menuLeft.isSecondaryMenuShowing || menuLeft.isMenuShowing) {
            menuLeft.showContent(true)
            return
        }
        if (!router.handleBack()) {
            super.onBackPressed()
        }
    }

    override fun setTopic(gameSettings: GameSettings) {
        router.pushController(RouterTransaction.with(GamePreviewController(gameSettings))
                .popChangeHandler(VerticalChangeHandler())
                .pushChangeHandler(VerticalChangeHandler()).tag(GamePreviewController.TAG))
        closeMenu()
    }

    override fun setSelectedLetter(letter: Letter) {
        router.setRoot(RouterTransaction.with(HomeController(letter))
                .popChangeHandler(VerticalChangeHandler())
                .pushChangeHandler(VerticalChangeHandler()))
        closeMenu()
    }

    override fun setAvailableTopics(topics: List<GameSettings>) {
        rightMenu.setTopics(topics)
    }

    override fun setAlphabet(alphabet: List<Letter>) {
        leftMenu.initAlphabet { letter ->
            presenter.onLetterSelected(letter)
        }
        leftMenu.setAlphabet(alphabet)
    }

    override fun closeMenu() {
        menuLeft.showContent()
    }

    @ProvidePresenter
    fun providePresenter(): WordPresentor {
        return App.component
                .plusWordPresenter()
                .presenter
    }

    private fun openChoseFile() {
        val choseIntent = Intent(Intent.ACTION_GET_CONTENT)
        choseIntent.addCategory(Intent.CATEGORY_OPENABLE)
        choseIntent.setType("file/*")
        val intent = Intent.createChooser(choseIntent, "Select file")
        startActivityForResult(intent, SELECT_FILE_RESULT)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == SELECT_FILE_RESULT) {
            val uri: Uri? = data?.data
            Log.d("MyLogs", "$uri")
            val words = CsvParser.parserToWords(getRealPathFromURI(uri))
            Log.d("MyLogs", "$words")
            presenter.updateWords(getRealPathFromURI(uri))
        }
    }

    fun getRealPathFromURI(contentUri: Uri?): String {
        val proj: String = MediaStore.Images.Media.DATA
        val cursor = getContentResolver().query(contentUri, arrayOf(proj), null, null, null)
        if (cursor == null) return ""
        val column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)

        cursor.moveToFirst();
        return cursor.getString(column_index)
    }

    companion object {
        private const val SELECT_FILE_RESULT: Int = 1
    }
}