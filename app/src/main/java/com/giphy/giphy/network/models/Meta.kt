package com.giphy.giphy.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Meta {

    @SerializedName("status")
    @Expose
    var status: Int? = null
    @SerializedName("msg")
    @Expose
    var msg: String? = null
    @SerializedName("response_id")
    @Expose
    var responseId: String? = null

}