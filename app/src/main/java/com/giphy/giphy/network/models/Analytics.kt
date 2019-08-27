package com.giphy.giphy.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Analytics {

    @SerializedName("onload")
    @Expose
    var onload: Link? = null
    @SerializedName("onclick")
    @Expose
    var onclick: Link? = null
    @SerializedName("onsent")
    @Expose
    var onsent: Link? = null

}