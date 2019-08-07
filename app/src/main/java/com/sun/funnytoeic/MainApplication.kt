package com.sun.funnytoeic

import android.app.Application
import com.sun.funnytoeic.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    private val modules = listOf(appModule, networkModule, repositoryModule, viewModelModule)

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(modules)
        }
    }
}
