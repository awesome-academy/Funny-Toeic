package com.sun.funnytoeic.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import com.sun.funnytoeic.data.local.entity.HintImage

@Dao
interface HintImageDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(vararg hintImages: HintImage)
}
