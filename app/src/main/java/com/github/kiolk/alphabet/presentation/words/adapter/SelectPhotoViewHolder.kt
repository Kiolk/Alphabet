package com.github.kiolk.alphabet.presentation.words.adapter

import android.view.View
import android.widget.ImageView
import butterknife.BindView
import com.bumptech.glide.Glide
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.data.models.word.Word

class SelectPhotoViewHolder(itemView: View, private val listener: (word: Word) -> Unit) : BaseViewHolder<Word>(itemView) {

    @BindView(R.id.select_item_photo)
    lateinit var ivWordPicture : ImageView

    @BindView(R.id.iv_item_photo_correct)
    lateinit var ivResult : ImageView

    @BindView(R.id.iv_item_photo_wrong)
    lateinit var ivWrong: ImageView

    override fun onBindViewHolder(data: Word) {
        Glide.with(getContext())
                .load(data.image)
                .into(ivWordPicture)

        ivWordPicture.setOnClickListener {
            listener(data)
        }

        if(itemViewType == SelectPhotoAdapter.CORRECT){
            ivResult.visibility = View.VISIBLE
            ivWrong.visibility = View.GONE
        }else if(itemViewType == SelectPhotoAdapter.WRONG_ANSWER){
            ivResult.visibility = View.GONE
            ivWrong.visibility = View.VISIBLE
        }else{
            ivResult.visibility = View.GONE
            ivWrong.visibility = View.GONE
        }
    }
}
