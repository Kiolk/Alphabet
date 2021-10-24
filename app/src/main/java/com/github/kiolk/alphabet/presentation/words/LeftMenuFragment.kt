package com.github.kiolk.alphabet.presentation.words

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.github.kiolk.alphabet.R
import com.github.kiolk.alphabet.data.models.letter.Letter
import com.github.kiolk.alphabet.presentation.words.adapter.alphabet.AlphabetAdapter
import com.github.kiolk.alphabet.presentation.words.adapter.alphabet.AlphabetDecoration

class LeftMenuFragment : Fragment() {

    @BindView(R.id.rw_left_menu_alphabet)
    lateinit var rwAlphabet : RecyclerView

    private lateinit var alphabetAdapter : AlphabetAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.layout_left_menu, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    fun initAlphabet(listener : (Letter) -> Unit){
       context?.let {
           alphabetAdapter = AlphabetAdapter(it, emptyList()){ letter ->
                listener.invoke(letter)
            }
        }
        val layout = GridLayoutManager(context, 4, GridLayoutManager.VERTICAL, false)
        rwAlphabet.layoutManager = layout
        rwAlphabet.addItemDecoration(AlphabetDecoration())
        rwAlphabet.adapter = alphabetAdapter
    }

    fun setAlphabet(list : List<Letter>){
        alphabetAdapter.setItems(list)
    }
}
