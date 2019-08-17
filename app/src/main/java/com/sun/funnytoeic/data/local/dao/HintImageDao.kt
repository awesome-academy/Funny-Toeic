package com.sun.funnytoeic.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.sun.funnytoeic.data.local.entity.HintImage
import com.sun.funnytoeic.data.local.entity.HintImage.Companion.TABLE_NAME

@Dao
interface HintImageDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(vararg hintImages: HintImage)

    @Query("SELECT * FROM $TABLE_NAME WHERE $FIELD_VOCABULARY_ID = :vocabularyId")
    suspend fun getHintImages(vocabularyId: Long): List<HintImage>

    companion object {
        private const val FIELD_VOCABULARY_ID = HintImage.FIELD_VOCABULARY_ID
    }
}
