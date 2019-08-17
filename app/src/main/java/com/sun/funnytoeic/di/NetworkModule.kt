package com.sun.funnytoeic.di

import com.sun.funnytoeic.data.remote.ApiService
import org.koin.dsl.module

val networkModule = module {
    single { ApiService.create() }
}
