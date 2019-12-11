package ir.apptaste.android.model.persistence.entity

import androidx.room.Embedded
import androidx.room.Relation

data class InfoWithResultEntity(
    @Embedded
    val info: InfoEntity,

    @Relation(
        parentColumn = "infoId",
        entityColumn = "infoCreatorId",
        entity = ResultEntity::class
    )
    val results: List<ResultEntity>
)