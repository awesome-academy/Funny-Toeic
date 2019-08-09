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

    @Query("SELECT * FROM $TABLE_NAME ORDER BY RANDOM() LIMIT 1")
    suspend fun selectRandomVocabulary(): Vocabulary?

    @Query("SELECT COUNT(*) FROM $TABLE_NAME")
    suspend fun getNumberVocabularies(): Int

    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun getVocabularies(): List<Vocabulary>

    @Query("SELECT COUNT(*) FROM $TABLE_NAME WHERE $FIELD_LEARNED = 1")
    suspend fun getNumberLearnedVocabularies(): Int

    @Query("SELECT * FROM $TABLE_NAME WHERE $FIELD_LEARNED = 1")
    suspend fun getLearnedVocabularies(): List<Vocabulary>

    companion object {
        private const val TABLE_NAME = Vocabulary.TABLE_NAME
        private const val FIELD_LEARNED = Vocabulary.FIELD_LEARNED
    }
}
