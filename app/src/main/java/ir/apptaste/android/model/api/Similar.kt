package ir.apptaste.android.model.api

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Similar(
    @SerializedName("Info")
    var info: ArrayList<ResultResponse>,

    @SerializedName("Results")
    var results: ArrayList<ResultResponse>
) : Serializable