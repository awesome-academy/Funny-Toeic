package com.sun.funnytoeic.data.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.*
import com.sun.funnytoeic.utils.Constants.DEFAULT_FIELD_ID
import kotlinx.android.parcel.Parcelize

@Entity(
    tableName = HintImage.TABLE_NAME,
    indices = [Index(
        value = [HintImage.FIELD_VOCABULARY_ID]
    )],
    foreignKeys = [ForeignKey(
        entity = Vocabulary::class,
        parentColumns = [Vocabulary.FIELD_ID],
        childColumns = [HintImage.FIELD_VOCABULARY_ID]
    )]
)
@Parcelize
data class HintImage(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = FIELD_ID)
    var id: Long = DEFAULT_FIELD_ID,
    @NonNull
    @ColumnInfo(name = FIELD_VOCABULARY_ID)
    val vocabularyId: Long,
    @NonNull
    @ColumnInfo(name = FIELD_URL)
    val url: String
) : Parcelable {

    companion object {
        const val TABLE_NAME = "hint_images"
        const val FIELD_ID = "id"
        const val FIELD_VOCABULARY_ID = "vocabulary_id"
        const val FIELD_URL = "url"
    }
}
