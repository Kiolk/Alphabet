package com.github.kiolk.alphabet.presentation.words.adapter

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class SelectPhotoDecorator(private val padding: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {

        val position = parent.getChildAdapterPosition(view)

        with(outRect) {
           when(position){
               0 -> {
                   right = padding
                   bottom = padding
               }
               2 -> {
                   left = padding
                   bottom = padding
               }
               1 -> {
                   right = padding
                   top = padding
               }
               3 -> {
                   left= padding
                   top= padding
               }
           }
        }
    }
}