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

    @BindView(R.id.result_item_photo)
    lateinit var ivResult : ImageView

    override fun onBindViewHolder(data: Word) {
        Glide.with(getContext())
                .load(data.image)
                .into(ivWordPicture)

        ivWordPicture.setOnClickListener {
            listener(data)
        }

        if(itemViewType == SelectPhotoAdapter.CORRECT){
            ivResult.visibility = View.VISIBLE
            ivResult.setImageDrawable(getContext().resources.getDrawable(R.drawable.ic_sun))
        }else if(itemViewType == SelectPhotoAdapter.WRONG_ANSWER){
            ivResult.visibility = View.VISIBLE
            ivResult.setImageDrawable(getContext().resources.getDrawable(R.drawable.ic_sad))
        }else{
            ivResult.visibility = View.GONE
        }
    }
}
