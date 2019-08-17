package com.sun.funnytoeic.data.repository.impl

import com.sun.funnytoeic.data.local.dao.HintImageDao
import com.sun.funnytoeic.data.local.dao.VocabularyDao
import com.sun.funnytoeic.data.local.entity.HintImage
import com.sun.funnytoeic.data.local.entity.Vocabulary
import com.sun.funnytoeic.data.remote.ApiService
import com.sun.funnytoeic.data.repository.VocabularyRepository
import com.sun.funnytoeic.data.repository.VocabularyRepository.Companion.NUMBER_HINT_IMAGES

class VocabularyRepositoryImpl(
    private val vocabularyDao: VocabularyDao,
    private val hintImageDao: HintImageDao,
    private val apiService: ApiService
) : VocabularyRepository {

    override suspend fun getNumberVocabularies() = vocabularyDao.getNumberVocabularies()

    override suspend fun getVocabularies() = vocabularyDao.getVocabularies()

    override suspend fun getNumberLearnedVocabularies() =
        vocabularyDao.getNumberLearnedVocabularies()

    override suspend fun getLearnedVocabularies() = vocabularyDao.getLearnedVocabularies()

    override suspend fun getRandomVocabulary() = vocabularyDao.selectRandomVocabulary()

    @Synchronized
    override suspend fun learnVocabulary(vocabulary: Vocabulary) =
        vocabularyDao.update(vocabulary.apply { learned = true })

    @Synchronized
    override suspend fun forgetVocabulary(vocabulary: Vocabulary) =
        vocabularyDao.update(vocabulary.apply { learned = false })

    override suspend fun getLocalHintImages(vocabulary: Vocabulary) =
        hintImageDao.getHintImages(vocabulary.id).take(NUMBER_HINT_IMAGES)

    override suspend fun getRemoteHintImages(vocabulary: Vocabulary) =
        apiService.searchImageByKeywordAsync(vocabulary.word).value.take(NUMBER_HINT_IMAGES)
            .map { HintImage(it, vocabulary) }

    override suspend fun insertHintImages(hintImages: List<HintImage>) =
        hintImages.forEach { hintImageDao.insert(it) }

    override suspend fun getHintImages(vocabulary: Vocabulary): List<HintImage> {
        var hintImages = getLocalHintImages(vocabulary)
        if (hintImages.isEmpty()) {
            hintImages = getRemoteHintImages(vocabulary)
            insertHintImages(hintImages)
        }
        return hintImages
    }
}
