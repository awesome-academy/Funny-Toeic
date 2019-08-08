package com.sun.funnytoeic.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.huma.room_for_asset.RoomAsset
import com.sun.funnytoeic.data.local.AppDatabase.Companion.DATABASE_VERSION
import com.sun.funnytoeic.data.local.dao.HintImageDao
import com.sun.funnytoeic.data.local.dao.VocabularyDao
import com.sun.funnytoeic.data.local.entity.HintImage
import com.sun.funnytoeic.data.local.entity.Vocabulary

@Database(
    entities = [Vocabulary::class, HintImage::class],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract val hintImageDao: HintImageDao
    abstract val vocabularyDao: VocabularyDao

    companion object {

        private const val DATABASE_NAME = "AppDatabase.db"

        const val DATABASE_VERSION = 2

        @Volatile
        private var INSTANCE: AppDatabase? = null

        @JvmStatic
        fun getInstance(context: Context) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) =
            RoomAsset.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DATABASE_NAME
            ).build()
    }
}
