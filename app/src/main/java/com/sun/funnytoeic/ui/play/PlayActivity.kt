package com.sun.funnytoeic.ui.play

import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.flexbox.FlexDirection.ROW
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent.CENTER
import com.sun.funnytoeic.R
import com.sun.funnytoeic.databinding.ActivityPlayBinding
import com.sun.funnytoeic.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_play.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayActivity : BaseActivity<ActivityPlayBinding, PlayActivityViewModel>(),
    View.OnClickListener {

    override val viewModel: PlayActivityViewModel by viewModel()
    override val layoutId = R.layout.activity_play
    private val hintImagesAdapter by lazyOf(HintImagesAdapter(mutableListOf()))
    private val selectedAnswerAdapter by lazyOf(CharactersAdapter(mutableListOf(), this))

    override fun initView() {
        hideActionBar()
        recyclerHintImages?.run {
            layoutManager = GridLayoutManager(this@PlayActivity, HINT_IMAGES_SPAN)
            adapter = hintImagesAdapter
        }
        recyclerSelectChars?.run {
            layoutManager = FlexboxLayoutManager(context, ROW).apply { justifyContent = CENTER }
            adapter = selectedAnswerAdapter
        }
    }

    override fun observeViewModel() = viewModel.run {
        vocabulary.observe(this@PlayActivity, Observer {
            selectedAnswerAdapter.updateData(it.word.toList())
        })
        hintImages.observe(this@PlayActivity, Observer {
            hintImagesAdapter.updateData(it)
        })
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.textSelectedCharacter -> onClickSelect(view as TextView)
        }
    }

    private fun onClickSelect(textView: TextView) {
    }

    companion object {
        private const val HINT_IMAGES_SPAN = 2
    }
}
