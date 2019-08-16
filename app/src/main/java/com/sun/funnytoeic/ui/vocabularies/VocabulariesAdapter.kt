package com.sun.funnytoeic.ui.vocabularies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.sun.funnytoeic.R
import com.sun.funnytoeic.data.local.entity.Vocabulary
import com.sun.funnytoeic.ui.base.UpdatableAdapter
import com.sun.funnytoeic.utils.replaceAll
import kotlinx.android.synthetic.main.item_vocabulary.view.*

class VocabulariesAdapter(
    private val vocabularies: MutableList<Vocabulary>
) : UpdatableAdapter<Vocabulary, VocabulariesAdapter.ViewHolder>(vocabularies) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(viewType, parent, false)
    )

    override fun getItemViewType(position: Int) = if (vocabularies[position].learned) {
        R.layout.item_vocabulary_learned
    } else {
        R.layout.item_vocabulary
    }

    class ViewHolder(view: View) : UpdatableAdapter.ViewHolder<Vocabulary>(view) {

        private val textWord by lazyOf(view.textItemWord)
        private val textDefinition by lazyOf(view.textItemDefinition)

        override fun bindData(item: Vocabulary) {
            textWord?.text = item.word
            textDefinition?.text = item.definition
        }
    }
}
