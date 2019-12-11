package ir.apptaste.android.model.persistence.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "info_table")
data class InfoEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "infoId")
    val infoId: Int,

    @ColumnInfo(name = "infoName")
    val infoName: String,

    @ColumnInfo(name = "infoType")
    val infoType: String
)