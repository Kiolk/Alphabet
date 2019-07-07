package com.github.kiolk.alphabet.presentation.adapters

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import com.github.kiolk.alphabet.utils.toPx

class TopicDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)

        with(outRect) {
            if (position % 2 == 0) {
                right = 3.toPx
                left = 0
                top = 0
                bottom = 10.toPx
            } else if (position % 2 == 1) {
                right = 0
                left = 3.toPx
                top = 0
                bottom = 10.toPx
            }
        }
    }
}