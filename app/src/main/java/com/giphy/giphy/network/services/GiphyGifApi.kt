package com.giphy.giphy.network.services

import com.giphy.giphy.network.models.GifData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyGifApi {

    @GET("gifs/search")
    fun search(@Query("q") query: String,
               @Query("offset") offset: Int = 0,
               @Query("limit") limit: Int = 25): Call<List<GifData>>

}
