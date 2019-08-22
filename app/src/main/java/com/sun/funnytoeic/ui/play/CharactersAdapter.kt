package com.sun.funnytoeic.ui.play

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sun.funnytoeic.R
import com.sun.funnytoeic.ui.base.UpdatableAdapter
import kotlinx.android.synthetic.main.item_answer_selected.view.*

class CharactersAdapter(
    characters: MutableList<Char>,
    private val onClickListener: View.OnClickListener
) : UpdatableAdapter<Char, CharactersAdapter.ViewHolder>(characters) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_answer_selected, parent, false),
        onClickListener
    )

    class ViewHolder(
        view: View,
        onClickListener: View.OnClickListener
    ) : UpdatableAdapter.ViewHolder<Char>(view) {

        private val textCharacter = view.textSelectedCharacter

        init {
            textCharacter?.setOnClickListener(onClickListener)
        }

        override fun bindData(item: Char) {
            textCharacter?.text = item.toString()
        }
    }
}
