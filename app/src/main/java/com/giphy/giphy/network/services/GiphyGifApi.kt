package com.giphy.giphy.network.services

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GiphyGifApi{

    @GET("gifs/search")
    fun search(@Query("q") query: String): Call<List<Area>>


}
