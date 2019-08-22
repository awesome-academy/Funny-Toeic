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
    private val _numberVocabularies = MutableLiveData<Int>()
    private val _numberLearnedVocabularies = MutableLiveData<Int>()

    val vocabulary: LiveData<Vocabulary> get() = _vocabulary
    val hintImages: LiveData<List<HintImage>> get() = _hintImages
    val result: LiveData<PlayResult> get() = _result
    val numberVocabularies: LiveData<Int> get() = _numberVocabularies
    val numberLearnedVocabularies: LiveData<Int> get() = _numberLearnedVocabularies

    init {
        viewModelScope.launch {
            loadRandomVocabulary()
            loadHintImages()
            setDefaultResult()
            loadVocabulariesNumber()
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

    private suspend fun loadVocabulariesNumber() {
        _numberVocabularies.value = repository.getNumberVocabularies()
        _numberLearnedVocabularies.value = repository.getNumberLearnedVocabularies()
    }

    private fun updateLearnVocabulary() = viewModelScope.launch {
        when (result.value) {
            CORRECT -> learnVocabulary()
            WRONG -> forgetVocabulary()
            else -> {
            }
        }
    }

    private suspend fun learnVocabulary() =
        vocabulary.value?.let { repository.learnVocabulary(it) }

    private suspend fun forgetVocabulary() =
        vocabulary.value?.let { if (it.learned) repository.forgetVocabulary(it) }

    fun checkResult(answer: String?) {
        if (checkAnswerLength(answer)) {
            _result.value = if (vocabulary.value?.word == answer) CORRECT else WRONG
            updateLearnVocabulary()
        }
    }

    private fun getWordLength() = vocabulary.value?.run { word.length } ?: 0

    private fun checkAnswerLength(answer: String?) = answer?.length == getWordLength()
}
