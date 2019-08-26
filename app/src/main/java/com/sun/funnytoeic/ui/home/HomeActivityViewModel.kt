package com.sun.funnytoeic.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sun.funnytoeic.data.repository.VocabularyRepository
import com.sun.funnytoeic.ui.base.BaseViewModel
import com.sun.funnytoeic.utils.Constants.MAX_PERCENT
import com.sun.funnytoeic.utils.Constants.NONE_VOCABULARY
import com.sun.funnytoeic.utils.Constants.NOT_ZERO_VALUE
import kotlinx.coroutines.launch

class HomeActivityViewModel(
    private val repository: VocabularyRepository
) : BaseViewModel() {

    private val _numberVocabularies = MutableLiveData<Int>()
    private val _numberLearnedVocabularies = MutableLiveData<Int>()
    private val _currentLevel = MutableLiveData<Int>()
    private val learnedPercent: Int
        get() {
            val numberLearned = _numberLearnedVocabularies.value ?: NONE_VOCABULARY
            val numberTotal = _numberVocabularies.value ?: NOT_ZERO_VALUE
            return numberLearned * MAX_PERCENT / numberTotal
        }

    val currentLevel: LiveData<Int> get() = _currentLevel


    init {
        viewModelScope.launch {
            _numberVocabularies.value = repository.getNumberVocabularies()
            _numberLearnedVocabularies.value = repository.getNumberLearnedVocabularies()
            _currentLevel.value = learnedPercent
        }
    }
}
