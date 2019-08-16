package com.sun.funnytoeic.ui.vocabularies

import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sun.funnytoeic.R
import com.sun.funnytoeic.databinding.ActivityVocabulariesBinding
import com.sun.funnytoeic.ui.base.BaseActivity
import com.sun.funnytoeic.ui.home.HomeActivityArgs
import kotlinx.android.synthetic.main.activity_vocabularies.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class VocabulariesActivity :
    BaseActivity<ActivityVocabulariesBinding, VocabulariesActivityViewModel, VocabulariesActivityArgs>(),
    View.OnClickListener {

    override val viewModel: VocabulariesActivityViewModel by viewModel()
    override val layoutId = R.layout.activity_vocabularies
    override val args by lazyOf(VocabulariesActivityArgs())
    private val adapter by lazyOf(VocabulariesAdapter(mutableListOf()))
    private var showingLearned = false

    override fun initView() {
        hideActionBar()
        recyclerVocabularies?.run {
            layoutManager = LinearLayoutManager(this@VocabulariesActivity)
            adapter = this@VocabulariesActivity.adapter
        }
        initListeners()
    }

    override fun observeViewModel() = viewModel.run {
        vocabularies.observe(this@VocabulariesActivity, Observer { adapter.updateData(it) })
    }

    override fun onClick(view: View?) {
        when (view) {
            imageHomeButton -> startActivity(HomeActivityArgs().intent(this))
            textLearned -> changeShowingMode()
        }
    }

    private fun initListeners() {
        imageHomeButton?.setOnClickListener(this)
        textLearned?.setOnClickListener(this)
    }

    private fun changeShowingMode() {
        showingLearned = !showingLearned
        updateView()
    }

    private fun updateView() = if (showingLearned) {
        textLearned?.text = getString(R.string.label_show_all_words)
        viewModel.loadLearnedVocabularies()
    } else {
        textLearned?.text = getString(R.string.label_show_learned_words)
        viewModel.loadVocabularies()
    }
}
