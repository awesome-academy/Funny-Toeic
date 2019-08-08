package com.sun.funnytoeic.di

import com.sun.funnytoeic.ui.splash.SplashActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashActivityViewModel(get()) }
}
