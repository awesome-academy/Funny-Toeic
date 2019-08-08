package com.sun.funnytoeic.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sun.funnytoeic.data.local.entity.Vocabulary

@Dao
interface VocabularyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg vocabularies: Vocabulary)

    @Update
    suspend fun update(vararg vocabularies: Vocabulary)

    @Query("SELECT COUNT(*) FROM $TABLE_NAME")
    suspend fun getNumberVocabularies(): Int

    companion object {
        private const val TABLE_NAME = Vocabulary.TABLE_NAME
    }
}
