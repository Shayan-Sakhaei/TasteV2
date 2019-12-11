package ir.apptaste.android.model

import io.reactivex.Single
import ir.apptaste.android.model.api.ApiResponse
import ir.apptaste.android.model.api.TasteWebService
import ir.apptaste.android.model.persistence.ResultDao
import javax.inject.Inject

class Repository @Inject constructor(
    private val tasteWebService: TasteWebService,
    private val resultDao: ResultDao
) {

    fun fetchResult(userQuery: String, userType: String, userLimit: String): Single<ApiResponse> {
        return tasteWebService.fetchResults(userQuery, userType, userLimit)
    }
}