package com.github.kiolk.alphabet.presentation.words

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import butterknife.BindView
import butterknife.ButterKnife
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.data.models.game.GameSettings
import com.github.kiolk.alphabet.presentation.adapters.TopicAdapter

class RightMenuFragment : Fragment() {

    @BindView(R.id.rw_right_menu_books_list)
    lateinit var rwBooksList : RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.lyaout_right_menu, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    fun setTopics(topics : List<GameSettings>){
        rwBooksList.layoutManager = GridLayoutManager(context, 2, GridLayout.VERTICAL, false)//LinearLayoutManager(context)
        rwBooksList.adapter = context?.let {
            TopicAdapter(topics, it, object : TopicAdapter.OnItemClickListener {
                override fun onItemClick(settings: GameSettings) {
                    (activity as MenuListenerView).setTopic(settings)
                }
            })

        }
    }
}