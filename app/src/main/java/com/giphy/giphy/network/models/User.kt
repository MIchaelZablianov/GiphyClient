package com.giphy.giphy.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User {

    @SerializedName("avatar_url")
    @Expose
    var avatarUrl: String? = null
    @SerializedName("banner_url")
    @Expose
    var bannerUrl: String? = null
    @SerializedName("profile_url")
    @Expose
    var profileUrl: String? = null
    @SerializedName("username")
    @Expose
    var username: String? = null
    @SerializedName("display_name")
    @Expose
    var displayName: String? = null
    @SerializedName("is_verified")
    @Expose
    var isVerified: Boolean? = null

}