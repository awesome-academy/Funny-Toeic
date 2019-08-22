package com.sun.funnytoeic.ui.play

import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.flexbox.FlexDirection.ROW_REVERSE
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent.CENTER
import com.sun.funnytoeic.R
import com.sun.funnytoeic.databinding.ActivityPlayBinding
import com.sun.funnytoeic.ui.base.BaseActivity
import com.sun.funnytoeic.ui.play.PlayResult.UNCOMPLETED
import com.sun.funnytoeic.utils.assignViews
import com.sun.funnytoeic.utils.gone
import com.sun.funnytoeic.utils.show
import com.sun.funnytoeic.utils.toShuffledMutableList
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
            layoutManager = FlexboxLayoutManager(this@PlayActivity, ROW_REVERSE).apply {
                justifyContent = CENTER
            }
            adapter = selectedAnswerAdapter
        }
        assignViews(buttonNext)
    }

    override fun observeViewModel() = viewModel.run {
        vocabulary.observe(this@PlayActivity, Observer { vocabulary ->
            selectedAnswerAdapter.updateData(vocabulary.word.toShuffledMutableList())
        })
        result.observe(this@PlayActivity, Observer { result ->
            if (result != UNCOMPLETED) showResult()
        })
        hintImages.observe(this@PlayActivity, Observer { hintImages ->
            hintImagesAdapter.updateData(hintImages)
        })
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.textSelectedCharacter -> onClickSelect(view as TextView)
            R.id.buttonNext -> PlayActivityArgs.instance().launch(this)
        }
    }

    private fun onClickSelect(textSelectedCharacter: TextView) {
        textSelectedCharacter.gone()
        textAnswer?.text = COMBINE_STRING.format(textAnswer?.text, textSelectedCharacter.text)
        viewModel.checkResult(textAnswer?.text.toString())
    }

    private fun showResult() {
        recyclerSelectChars?.gone()
        textTrueAnswer?.show()
        textDefinition?.show()
        buttonNext?.show()
    }

    companion object {
        private const val HINT_IMAGES_SPAN = 2
        private const val COMBINE_STRING = "%s%s"
    }
}
