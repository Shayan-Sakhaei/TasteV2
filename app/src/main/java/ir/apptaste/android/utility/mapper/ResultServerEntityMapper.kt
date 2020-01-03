package ir.apptaste.android.utility.mapper

import ir.apptaste.android.model.api.ResultResponse
import ir.apptaste.android.model.persistence.entity.ResultEntity
import javax.inject.Inject

class ResultServerEntityMapper @Inject constructor() : Mapper<ResultResponse, ResultEntity> {
    override fun map(item: ResultResponse): ResultEntity =
        ResultEntity(
            item.name,
            item.type,
            item.wTeaser,
            item.wUrl,
            item.yUrl,
            item.yID
        )
}