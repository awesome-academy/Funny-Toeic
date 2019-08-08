package com.sun.funnytoeic.data.repository

interface VocabularyRepository {
    suspend fun getNumberVocabularies(): Int
}
