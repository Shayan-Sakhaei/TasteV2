package ir.apptaste.android.model.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TasteWebService {
    @GET("api/similar?k=350475-Taste-HPSHA63Z&?info=1&verbose=1")
    fun fetchResults(
        @Query("q") query: String,
        @Query("type") type: String,
        @Query("li,it") limit: String
    ): Single<ApiResponse>
}