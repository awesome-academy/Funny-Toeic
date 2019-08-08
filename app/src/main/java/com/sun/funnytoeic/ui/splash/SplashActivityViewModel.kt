package com.sun.funnytoeic.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sun.funnytoeic.data.repository.impl.VocabularyRepositoryImpl
import com.sun.funnytoeic.ui.base.BaseViewModel
import com.sun.funnytoeic.utils.Constants.NUMBER_VOCABULARIES
import com.sun.funnytoeic.utils.Constants.VALUE_100
import kotlinx.coroutines.launch

class SplashActivityViewModel(
    private val repository: VocabularyRepositoryImpl
) : BaseViewModel() {

    private val _loadingDataProgress = MutableLiveData<Int>()
    val loadingDataProgress: LiveData<Int>
        get() = _loadingDataProgress

    init {
        viewModelScope.launch {
            _loadingDataProgress.value =
                repository.getNumberVocabularies() * VALUE_100 / NUMBER_VOCABULARIES
        }
    }
}
