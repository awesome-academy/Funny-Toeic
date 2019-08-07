package com.sun.funnytoeic.data.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.sun.funnytoeic.utils.Constants.DEFAULT_FIELD_ID
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Entity(
    tableName = Vocabulary.TABLE_NAME,
    indices = [Index(
        value = [Vocabulary.FIELD_WORD],
        unique = true
    )]
)
@Parcelize
data class Vocabulary(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = FIELD_ID)
    var id: Long = DEFAULT_FIELD_ID,
    @NonNull
    @ColumnInfo(name = FIELD_WORD)
    val word: String,
    @NonNull
    @ColumnInfo(name = FIELD_DEFINITION)
    val definition: String,
    @NonNull
    @ColumnInfo(name = FIELD_LEARNED)
    val learned: Boolean = false
) : Parcelable {

    constructor(jsonObject: JSONObject) : this(
        word = jsonObject.optString(FIELD_WORD),
        definition = jsonObject.optString(FIELD_DEFINITION)
    )

    companion object {
        const val TABLE_NAME = "vocabularies"
        const val FIELD_ID = "id"
        const val FIELD_WORD = "word"
        const val FIELD_DEFINITION = "definition"
        const val FIELD_LEARNED = "learned"
    }
}
