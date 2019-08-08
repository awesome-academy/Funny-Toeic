package com.sun.funnytoeic.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sun.funnytoeic.data.local.entity.Vocabulary
import com.sun.funnytoeic.data.repository.VocabularyRepository
import com.sun.funnytoeic.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class HomeActivityViewModel(
    private val repository: VocabularyRepository
) : BaseViewModel() {

    private val _learnedVocabularies = MutableLiveData<List<Vocabulary>>()
    val learnedVocabularies: LiveData<List<Vocabulary>>
        get() = _learnedVocabularies

    init {
        viewModelScope.launch {
            _learnedVocabularies.value = repository.getLearnedVocabularies()
        }
    }
}
