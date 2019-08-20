package com.sun.funnytoeic.data.repository

import com.sun.funnytoeic.data.local.entity.HintImage
import com.sun.funnytoeic.data.local.entity.Vocabulary

interface VocabularyRepository {
    suspend fun getNumberVocabularies(): Int
    suspend fun getVocabularies(): List<Vocabulary>
    suspend fun getNumberLearnedVocabularies(): Int
    suspend fun getLearnedVocabularies(): List<Vocabulary>
    suspend fun getRandomVocabulary(): Vocabulary?
    suspend fun learnVocabulary(vocabulary: Vocabulary)
    suspend fun getLocalHintImages(vocabulary: Vocabulary): List<HintImage>
    suspend fun getRemoteHintImages(vocabulary: Vocabulary): List<HintImage>
    suspend fun insertHintImages(hintImages: List<HintImage>)
    suspend fun getHintImages(vocabulary: Vocabulary): List<HintImage>

    companion object {
        const val NUMBER_HINT_IMAGES = 4
    }
}
