package com.github.kiolk.alphabet.presentation.words

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.WindowManager
import butterknife.BindView
import butterknife.ButterKnife
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bluelinelabs.conductor.ChangeHandlerFrameLayout
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.github.kiolk.alphabet.App
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.data.models.game.GameResult
import com.github.kiolk.alphabet.data.models.game.GameSettings
import com.github.kiolk.alphabet.data.models.letter.Letter
import com.github.kiolk.alphabet.data.models.topic.Topic
import com.github.kiolk.alphabet.presentation.dialogs.EndGameDialog
import com.github.kiolk.alphabet.presentation.game.game.GameController
import com.github.kiolk.alphabet.presentation.home.HomeController
import com.github.kiolk.alphabet.presentation.main.MainController
import com.github.kiolk.alphabet.presentation.settings.SettingsController
import com.github.kiolk.alphabet.utils.CsvParser
import com.github.kiolk.alphabet.utils.toPx
import com.github.kiolk.common.presentation.base.BaseView
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
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        }


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
            router.setRoot(RouterTransaction.with(MainController()).tag((MainController.TAG)))
        }
    }

    override fun onBackPressed() {
        if (menuLeft.isSecondaryMenuShowing || menuLeft.isMenuShowing) {
            menuLeft.showContent(true)
            return
        }

        if (router.backstack[0].tag() == EndGameDialog.TAG) {
            (router.getControllerWithTag(GameController.TAG) as? GameController)?.closeGame()
            return
        }

        if (router.backstack.size == 1) {
            presenter.showWordsTopic()
        }

        if (!router.handleBack()) {
            presenter.onBackPressed()
        }
    }

    override fun endGame() {
        super.onBackPressed()
    }

    override fun showMain() {
        router.setRoot(RouterTransaction.with(MainController())
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler()).tag(MainController.TAG))
    }

    override fun onStartTopic(gameResult: GameResult) {
        router.getControllerWithTag(GameController.TAG)?.let { router.popController(it) }
        router.pushController(RouterTransaction.with(GameController(gameResult))
                .popChangeHandler(FadeChangeHandler())
                .pushChangeHandler(FadeChangeHandler()).tag(GameController.TAG))
        closeMenu()
    }

    override fun onTopicClick(gameSettings: GameSettings) {
        presenter.onTopicClick(gameSettings)
    }

    override fun onWordsTopicClick(topic: Topic) {
        presenter.onWordsTopicClick(topic)
    }

    override fun setSelectedLetter(letter: Letter) {
        router.popToTag(MainController.TAG)
        router.setRoot(RouterTransaction.with(HomeController(letter))
                .popChangeHandler(FadeChangeHandler())
                .pushChangeHandler(FadeChangeHandler()))
        closeMenu()
    }

    override fun setAvailableTopics(topics: List<GameSettings>) {
        rightMenu.setTopics(topics)
    }

    override fun setWordTopics(topics: List<Topic>) {
        rightMenu.setWordsTopic(topics)
    }

    override fun initAlphabet() {
        leftMenu.initAlphabet { letter ->
            presenter.onLetterSelected(letter)
        }
    }

    override fun setAlphabet(alphabet: List<Letter>) {
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
        val cursor = getContentResolver().query(contentUri!!, arrayOf(proj), null, null, null)
        if (cursor == null) return ""
        val column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)

        cursor.moveToFirst();
        return cursor.getString(column_index)
    }

    override fun setStatusBarColor(colorRes: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = resources.getColor(colorRes)
        }
    }

    fun showSettings() {
        router.popToTag(MainController.TAG)
        router.pushController(RouterTransaction.with(SettingsController())
                .pushChangeHandler((FadeChangeHandler()))
                .popChangeHandler(FadeChangeHandler()).tag(SettingsController.TAG))
        closeMenu()
    }

    companion object {
        private const val SELECT_FILE_RESULT: Int = 1
    }
}