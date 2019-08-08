package com.sun.funnytoeic.data.repository.impl

import com.sun.funnytoeic.data.local.dao.VocabularyDao
import com.sun.funnytoeic.data.repository.VocabularyRepository

class VocabularyRepositoryImpl(
    private val vocabularyDao: VocabularyDao
) : VocabularyRepository {

    override suspend fun getNumberVocabularies() = vocabularyDao.getNumberVocabularies()
}
