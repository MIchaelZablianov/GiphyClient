package com.giphy.giphy.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Link {
    @SerializedName("url")
    @Expose
    var url: String? = null
}