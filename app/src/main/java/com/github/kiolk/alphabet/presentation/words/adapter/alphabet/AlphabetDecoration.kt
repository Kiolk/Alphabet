package com.github.kiolk.alphabet.presentation.words.adapter.alphabet

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class AlphabetDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)

        with(outRect) {
            right = 5
            left = 5
            top = 5
            bottom = 5

        }
    }
}