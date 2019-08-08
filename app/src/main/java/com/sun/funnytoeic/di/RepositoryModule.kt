package com.sun.funnytoeic.di

import com.sun.funnytoeic.data.local.AppDatabase
import com.sun.funnytoeic.data.repository.VocabularyRepository
import com.sun.funnytoeic.data.repository.impl.VocabularyRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<VocabularyRepository> { VocabularyRepositoryImpl(get()) }
    single { AppDatabase.getInstance(get()) }
    single { AppDatabase.getInstance(get()).vocabularyDao }
}
