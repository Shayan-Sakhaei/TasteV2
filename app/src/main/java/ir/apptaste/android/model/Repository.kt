package ir.apptaste.android.model

import io.reactivex.Observable
import io.reactivex.Single
import ir.apptaste.android.model.api.ApiResponse
import ir.apptaste.android.model.api.ResultResponse
import ir.apptaste.android.model.api.TasteWebService
import ir.apptaste.android.model.persistence.ResultDao
import ir.apptaste.android.utility.mapper.ResultServerEntityMapper
import javax.inject.Inject

class Repository @Inject constructor(
    private val tasteWebService: TasteWebService,
    private val resultServerEntityMapper: ResultServerEntityMapper,
    private val resultDao: ResultDao
) {

    fun fetchAndSaveResult(
        userQuery: String,
        userType: String,
        userLimit: String
    ): Single<Optional<ArrayList<ResultResponse>>> {
        return tasteWebService.fetchResults(userQuery, userType, userLimit)
            .flatMap { response ->
                val body = response.body()
                if (!response.isSuccessful || body == null) {
                    return@flatMap Single.just(Optional.Failed<ArrayList<ResultResponse>>("failed to receive the data"))
                }

                val result = body.similar.results

                val entities = result.map {
                    resultServerEntityMapper.map(it)
                }

                Observable.fromIterable(entities)
                    .flatMapCompletable {
                        resultDao.insert(it)
                    }
                    .andThen(Single.just(Optional.Success(result)))
            }
            .onErrorReturnItem(
                Optional.Failed("rx Chain failed")
            )
    }
}