package com.sun.funnytoeic.ui.play

import android.media.MediaPlayer
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
import com.sun.funnytoeic.ui.home.HomeActivityArgs
import com.sun.funnytoeic.ui.play.PlayResult.CORRECT
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
        assignViews(buttonNext, imageHomeButton)
    }

    override fun observeViewModel() = viewModel.run {
        vocabulary.observe(this@PlayActivity, Observer { vocabulary ->
            selectedAnswerAdapter.updateData(vocabulary.word.toShuffledMutableList())
        })
        result.observe(this@PlayActivity, Observer { result ->
            if (result != UNCOMPLETED) showResult(result)
        })
        hintImages.observe(this@PlayActivity, Observer { hintImages ->
            hintImagesAdapter.updateData(hintImages)
        })
        numberLearnedVocabularies.observe(this@PlayActivity, Observer {
            updateTextCurrentLearned(it, numberVocabularies.value)
        })
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.textSelectedCharacter -> onClickSelect(view as TextView)
            R.id.buttonNext -> PlayActivityArgs.instance().launch(this)
            R.id.imageHomeButton -> HomeActivityArgs.instance().launch(this)
        }
    }

    private fun onClickSelect(textSelectedCharacter: TextView) {
        textSelectedCharacter.gone()
        textAnswer?.text = COMBINE_STRING.format(textAnswer?.text, textSelectedCharacter.text)
        viewModel.checkResult(textAnswer?.text.toString())
    }

    private fun showResult(result: PlayResult) {
        startSoundResult(result)
        recyclerSelectChars?.gone()
        textTrueAnswer?.show()
        textDefinition?.show()
        buttonNext?.show()
    }

    private fun startSoundResult(result: PlayResult) {
        val soundResourceId = if (result == CORRECT) R.raw.sound_correct else R.raw.sound_wrong
        MediaPlayer.create(this, soundResourceId).start()
    }

    private fun updateTextCurrentLearned(learned: Int?, total: Int?) {
        textCurrentLearned?.text = getString(R.string.label_current_learned).format(learned, total)
    }

    companion object {
        private const val HINT_IMAGES_SPAN = 2
        private const val COMBINE_STRING = "%s%s"
    }
}
