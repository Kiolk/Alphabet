package com.github.kiolk.alphabet.presentation.home

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.data.models.letter.Letter
import com.github.kiolk.alphabet.di.modules.presenter.HomePresenterModule
import com.github.kiolk.alphabet.presentation.base.controller.BaseController
import com.github.kiolk.alphabet.utils.BundleBuilder
import io.supercharge.shimmerlayout.ShimmerLayout

class HomeController : BaseController, HomeView {

    @BindView(R.id.tv_home_letter_title)
    lateinit var tvLetterTitle : TextView

    @BindView(R.id.iv_home_image)
    lateinit var ivLetterImage: ImageView

    @BindView(R.id.tv_home_example_word)
    lateinit var tvExampleWord: TextView

    @BindView(R.id.sl_preview_item_load_progress)
    lateinit var slLoadProgress: ShimmerLayout

    @InjectPresenter
    lateinit var presenter: HomePresenter

    constructor(letter: Letter) : super(BundleBuilder(Bundle()).setParseleable(BUNDLE_LETTER, letter).build())
    constructor(bundle: Bundle) : super(bundle)

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.controller_home, container, false)
        return view
    }

    override fun setTitle(title: String) {
        tvLetterTitle.text = title
    }

    override fun setExample(spannable: SpannableStringBuilder) {
        tvExampleWord.text = spannable
    }

    override fun setLetterImage(source: String) {
        slLoadProgress.visibility = View.VISIBLE
        slLoadProgress.startShimmerAnimation()
        Glide.with(ivLetterImage)
                .load(source)
                .addListener(object: RequestListener<Drawable> {
<<<<<<< Updated upstream
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
=======
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
>>>>>>> Stashed changes
                        if (this@HomeController::slLoadProgress.isInitialized) {
                            slLoadProgress.visibility = View.INVISIBLE
                            slLoadProgress.stopShimmerAnimation()
                        }
                        return false
                    }

<<<<<<< Updated upstream
                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
=======
                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
>>>>>>> Stashed changes
                        if (this@HomeController::slLoadProgress.isInitialized) {
                            slLoadProgress.visibility = View.INVISIBLE
                            slLoadProgress.stopShimmerAnimation()
                        }
                        return false
                    }
                })
                .error(R.drawable.ic_image_error)
                .into(ivLetterImage)
    }

    @ProvidePresenter
    fun providePresenter(): HomePresenter {
        return getApplicationComponent()
            .plusHomePresenter(
                HomePresenterModule(
                    args.getParcelable(BUNDLE_LETTER)
                        ?: Letter("a", "a", "a", letterWord = "Аўтобус")
                )
            )
                .presenter
    }

    companion object {
        const val BUNDLE_LETTER: String = "BUNDLE_LETTER"
    }
}