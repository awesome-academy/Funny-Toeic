package com.sun.funnytoeic.ui.vocabularies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sun.funnytoeic.data.local.entity.Vocabulary
import com.sun.funnytoeic.data.repository.VocabularyRepository
import com.sun.funnytoeic.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class VocabulariesActivityViewModel(
    private val repository: VocabularyRepository
) : BaseViewModel() {

    private val _vocabularies = MutableLiveData<List<Vocabulary>>()
    val vocabularies: LiveData<List<Vocabulary>>
        get() = _vocabularies

    init {
        loadVocabularies()
    }

    fun loadVocabularies() = viewModelScope.launch {
        _vocabularies.value = repository.getVocabularies()
    }

    fun loadLearnedVocabularies() = viewModelScope.launch {
        _vocabularies.value = repository.getLearnedVocabularies()
    }
}
