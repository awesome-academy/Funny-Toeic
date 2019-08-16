package com.sun.funnytoeic.ui.vocabularies

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.view.View
import android.widget.TextView
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

    override fun initView() {
        hideActionBar()
        recyclerVocabularies?.run {
            layoutManager = LinearLayoutManager(this@VocabulariesActivity)
            adapter = this@VocabulariesActivity.adapter
        }
        imageHomeButton?.setOnClickListener(this)
    }

    override fun observeViewModel() = viewModel.run {
        vocabularies.observe(this@VocabulariesActivity, Observer { adapter.updateData(it) })
    }

    override fun onClick(view: View?) {
        when (view) {
            imageHomeButton -> startActivity(HomeActivityArgs().intent(this))
        }
    }

}
