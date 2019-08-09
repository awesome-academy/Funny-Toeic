package com.sun.funnytoeic.data.repository.impl

import com.sun.funnytoeic.data.local.dao.VocabularyDao
import com.sun.funnytoeic.data.local.entity.Vocabulary
import com.sun.funnytoeic.data.repository.VocabularyRepository

class VocabularyRepositoryImpl(
    private val vocabularyDao: VocabularyDao
) : VocabularyRepository {

    override suspend fun getNumberVocabularies() = vocabularyDao.getNumberVocabularies()

    override suspend fun getVocabularies() = vocabularyDao.getVocabularies()

    override suspend fun getNumberLearnedVocabularies() =
        vocabularyDao.getNumberLearnedVocabularies()

    override suspend fun getLearnedVocabularies() = vocabularyDao.getLearnedVocabularies()

    override suspend fun getRandomVocabulary() = vocabularyDao.selectRandomVocabulary()

    override suspend fun learnVocabulary(vocabulary: Vocabulary) =
        vocabularyDao.update(vocabulary.apply { learned = true })
}
