package com.sun.funnytoeic.di

import com.sun.funnytoeic.ui.home.HomeActivityViewModel
import com.sun.funnytoeic.ui.play.PlayActivityViewModel
import com.sun.funnytoeic.ui.splash.SplashActivityViewModel
import com.sun.funnytoeic.ui.store.StoreActivityViewModel
import com.sun.funnytoeic.ui.vocabularies.VocabulariesActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashActivityViewModel(get()) }
    viewModel { HomeActivityViewModel(get()) }
    viewModel { VocabulariesActivityViewModel(get()) }
    viewModel { PlayActivityViewModel(get()) }
    viewModel { StoreActivityViewModel() }
}
