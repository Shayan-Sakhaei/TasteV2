package ir.apptaste.android.model.persistence.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "result_table")
data class ResultEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "resultId")
    val resultId: Int,

    val infoCreatorId: Long,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "type")
    val type: String,

    @ColumnInfo(name = "wikiTeaser")
    val wTeaser: String,

    @ColumnInfo(name = "wikiUrl")
    val wUrl: String,

    @ColumnInfo(name = "youtubeUrl")
    val yUrl: String,

    @ColumnInfo(name = "youtubeId")
    val yID: String
)