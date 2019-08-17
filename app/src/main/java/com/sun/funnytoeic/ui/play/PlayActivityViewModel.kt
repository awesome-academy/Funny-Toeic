package com.sun.funnytoeic.ui.play

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sun.funnytoeic.data.local.entity.HintImage
import com.sun.funnytoeic.data.local.entity.Vocabulary
import com.sun.funnytoeic.data.repository.VocabularyRepository
import com.sun.funnytoeic.ui.base.BaseViewModel
import com.sun.funnytoeic.ui.play.PlayResult.CORRECT
import com.sun.funnytoeic.ui.play.PlayResult.WRONG
import com.sun.funnytoeic.ui.play.PlayResult.UNCOMPLETED
import kotlinx.coroutines.launch

class PlayActivityViewModel(private val repository: VocabularyRepository) : BaseViewModel() {

    private val _vocabulary = MutableLiveData<Vocabulary>()
    private val _hintImages = MutableLiveData<List<HintImage>>()
    private val _result = MutableLiveData<PlayResult>()

    val vocabulary: LiveData<Vocabulary>
        get() = _vocabulary
    val hintImages: LiveData<List<HintImage>>
        get() = _hintImages
    val result: LiveData<PlayResult>
        get() = _result

    init {
        viewModelScope.launch {
            loadRandomVocabulary()
            loadHintImages()
            setDefaultResult()
        }
    }

    private fun setDefaultResult() {
        _result.value = UNCOMPLETED
    }

    private suspend fun loadRandomVocabulary() {
        _vocabulary.value = repository.getRandomVocabulary()
    }

    private suspend fun loadHintImages() {
        _vocabulary.value?.let {
            _hintImages.value = repository.getHintImages(it)
        }
    }

    private suspend fun updateLearnVocabulary() {
        _vocabulary.value?.let { vocab ->
            _result.value?.let {
                if (it == CORRECT) {
                    repository.learnVocabulary(vocab)
                } else if (it == WRONG && vocab.learned) {
                    repository.forgetVocabulary(vocab)
                }
            }
        }
    }

    fun checkResult(answer: String?) {
        answer?.let { ans ->
            _vocabulary.value?.word?.also { word ->
                if (ans.length == word.length) {
                    _result.value = if (ans == word) CORRECT else WRONG
                    viewModelScope.launch { updateLearnVocabulary() }
                }
            }
        }
    }
}
