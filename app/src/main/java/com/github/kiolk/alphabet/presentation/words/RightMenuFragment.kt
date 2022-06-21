package com.github.kiolk.alphabet.presentation.words

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.presentation.adapters.TopicAdapter
import com.github.kiolk.alphabet.presentation.adapters.TopicDecoration
import com.github.kiolk.alphabet.presentation.words.adapter.topic.WordsTopicAdapter
import com.github.kiolk.common.data.model.word.GameSettings
import com.github.kiolk.common.data.model.word.Topic

class RightMenuFragment : Fragment() {

    @BindView(R.id.rw_right_menu_books_list)
    lateinit var rwBooksList : RecyclerView

    @BindView(R.id.rw_right_menu_words_topic)
    lateinit var rwWordsTopic: RecyclerView

    private lateinit var wordsTopicAdapter: WordsTopicAdapter

    private var initial: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.lyaout_right_menu, container, false)
        ButterKnife.bind(this, view)
        rwBooksList.addItemDecoration(TopicDecoration())

        initial = true

        rwWordsTopic.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        wordsTopicAdapter = WordsTopicAdapter{
            (activity as MenuListenerView).onWordsTopicClick(it)
        }
        rwWordsTopic.adapter = wordsTopicAdapter
        return view
    }

    fun setTopics(topics : List<GameSettings>){
        rwBooksList.visibility = View.VISIBLE
        rwWordsTopic.visibility = View.GONE

        rwBooksList.layoutManager = GridLayoutManager(context, 2, GridLayout.VERTICAL, false)
        val adapter = TopicAdapter(topics, object : TopicAdapter.OnItemClickListener {
            override fun onItemClick(settings: GameSettings) {
                (activity as MenuListenerView).onTopicClick(settings)
            }
        })

        rwBooksList.adapter = adapter
    }

    fun setWordsTopic(topics: List<Topic>){
        rwBooksList.visibility = View.GONE
        rwWordsTopic.visibility = View.VISIBLE

        wordsTopicAdapter.setResources(topics, initial)
        initial = false
    }
}