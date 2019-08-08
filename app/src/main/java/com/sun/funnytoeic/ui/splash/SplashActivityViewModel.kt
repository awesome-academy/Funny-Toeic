package com.sun.funnytoeic.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sun.funnytoeic.data.repository.VocabularyRepository
import com.sun.funnytoeic.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class SplashActivityViewModel(
    private val repository: VocabularyRepository
) : BaseViewModel() {

    private val _done = MutableLiveData<Boolean>()
    val done: LiveData<Boolean>
        get() = _done

    init {
        _done.value = false
        viewModelScope.launch {
            repository.getNumberVocabularies()
            _done.value = true
        }
    }
}
