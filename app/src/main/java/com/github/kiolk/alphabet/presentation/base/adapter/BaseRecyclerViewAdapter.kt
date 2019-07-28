package com.github.kiolk.alphabet.presentation.base.adapter

import android.os.Handler
import android.support.v7.widget.RecyclerView
import com.github.kiolk.alphabet.presentation.words.adapter.BaseViewHolder

abstract class BaseRecyclerViewAdapter<T, VH : BaseViewHolder<*>> : RecyclerView.Adapter<VH>() {

    protected val resources = ArrayList<T>()

    override fun getItemCount(): Int = resources.size

    open fun setResources(list: List<T>, initial: Boolean = false) {
        resources.clear()
        resources.addAll(list)
        notifyDataSetChanged()

        Handler().postDelayed({
            notifyDataSetChanged()
        }, 100)
    }
}