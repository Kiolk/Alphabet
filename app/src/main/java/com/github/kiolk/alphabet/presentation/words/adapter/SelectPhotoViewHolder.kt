package com.github.kiolk.alphabet.presentation.words.adapter

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.data.models.word.Word
import io.supercharge.shimmerlayout.ShimmerLayout

class SelectPhotoViewHolder(itemView: View, private val listener: (word: Word) -> Unit) : BaseViewHolder<Word>(itemView) {

    @BindView(R.id.select_item_photo)
    lateinit var ivWordPicture: ImageView

    @BindView(R.id.iv_item_photo_correct)
    lateinit var ivResult: ImageView

    @BindView(R.id.iv_item_photo_wrong)
    lateinit var ivWrong: ImageView

    @BindView(R.id.tv_item_photo_close)
    lateinit var ivClose: TextView

    @BindView(R.id.photo_item_load_progress)
    lateinit var shimmerLayout: ShimmerLayout

    override fun onBindViewHolder(data: Word) {
        shimmerLayout.visibility = View.VISIBLE
        shimmerLayout.startShimmerAnimation()

        Glide.with(getContext())
                .load(data.image)
                .addListener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        if (this@SelectPhotoViewHolder::shimmerLayout.isInitialized) {
                            shimmerLayout.visibility = View.INVISIBLE
                            shimmerLayout.stopShimmerAnimation()
                        }

                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        if (this@SelectPhotoViewHolder::shimmerLayout.isInitialized) {
                            shimmerLayout.visibility = View.INVISIBLE
                            shimmerLayout.stopShimmerAnimation()
                        }

                        return false
                    }
                })
                .error(R.drawable.ic_image_error)
                .into(ivWordPicture)

        ivWordPicture.setOnClickListener {
            listener(data)
        }

        ivClose.visibility = View.GONE

        if (itemViewType == SelectPhotoAdapter.CORRECT) {
            ivResult.visibility = View.VISIBLE
            ivWrong.visibility = View.GONE
        } else if (itemViewType == SelectPhotoAdapter.WRONG_ANSWER) {
            ivResult.visibility = View.GONE
            ivWrong.visibility = View.VISIBLE
        } else if (itemViewType == SelectPhotoAdapter.HIDE) {
            ivClose.visibility = View.VISIBLE
        } else {
            ivResult.visibility = View.GONE
            ivWrong.visibility = View.GONE
        }
    }
}
