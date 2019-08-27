package com.giphy.giphy.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class GifResponseData {

    @SerializedName("data")
    @Expose
    var data: List<GifData>? = null
    @SerializedName("pagination")
    @Expose
    var pagination: Pagination? = null
    @SerializedName("meta")
    @Expose
    var meta: Meta? = null
}


