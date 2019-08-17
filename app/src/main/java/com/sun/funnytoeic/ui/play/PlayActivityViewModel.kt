package com.sun.funnytoeic.ui.play

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sun.funnytoeic.data.local.entity.HintImage
import com.sun.funnytoeic.data.local.entity.Vocabulary
import com.sun.funnytoeic.data.repository.VocabularyRepository
import com.sun.funnytoeic.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class PlayActivityViewModel(private val repository: VocabularyRepository) : BaseViewModel() {

    private val _vocabulary = MutableLiveData<Vocabulary>()
    val vocabulary: LiveData<Vocabulary>
        get() = _vocabulary

    private val _hintImages = MutableLiveData<List<HintImage>>()
    val hintImages: LiveData<List<HintImage>>
        get() = _hintImages

    init {
        loadRandomVocabulary()
    }

    private fun loadRandomVocabulary() = viewModelScope.launch {
        _vocabulary.value = repository.getRandomVocabulary()
        loadHintImages()
    }

    private fun loadHintImages() = viewModelScope.launch {
        _vocabulary.value?.also {
            _hintImages.value = repository.getHintImages(it)
        }
    }
}
