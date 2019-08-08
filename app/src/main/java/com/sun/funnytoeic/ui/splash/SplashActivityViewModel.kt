package com.sun.funnytoeic.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sun.funnytoeic.ui.base.BaseViewModel

class SplashActivityViewModel : BaseViewModel() {

    private val _loadingDataProgress = MutableLiveData<Int>()
    val loadingDataProgress: LiveData<Int>
        get() = _loadingDataProgress
}
