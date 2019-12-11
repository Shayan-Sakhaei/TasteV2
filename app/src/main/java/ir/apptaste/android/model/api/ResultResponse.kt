package ir.apptaste.android.model.api

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResultResponse(
    @SerializedName("Name")
    var name: String,
    @SerializedName("Type")
    var type: String,
    @SerializedName("wTeaser")
    var wTeaser: String,
    @SerializedName("wUrl")
    var wUrl: String,
    @SerializedName("yUrl")
    var yUrl: String,
    @SerializedName("yID")
    var yID: String
) : Serializable