package com.sun.funnytoeic.ui.play

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sun.funnytoeic.data.local.entity.Vocabulary
import com.sun.funnytoeic.data.repository.VocabularyRepository
import com.sun.funnytoeic.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class PlayActivityViewModel(private val repository: VocabularyRepository): BaseViewModel() {

    private val _vocabulary = MutableLiveData<Vocabulary>()
    val vocabulary: LiveData<Vocabulary>
        get() = _vocabulary

    init {
        loadRandomVocabulary()
    }

    fun loadRandomVocabulary() = viewModelScope.launch {
        _vocabulary.value = repository.getRandomVocabulary()
    }
}
