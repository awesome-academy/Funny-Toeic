package com.sun.funnytoeic.data.repository

import com.sun.funnytoeic.data.local.entity.Vocabulary

interface VocabularyRepository {
    suspend fun getNumberVocabularies(): Int
    suspend fun getVocabularies(): List<Vocabulary>
    suspend fun getNumberLearnedVocabularies(): Int
    suspend fun getLearnedVocabularies(): List<Vocabulary>
    suspend fun getRandomVocabulary(): Vocabulary?
    suspend fun learnVocabulary(vocabulary: Vocabulary?)
}
