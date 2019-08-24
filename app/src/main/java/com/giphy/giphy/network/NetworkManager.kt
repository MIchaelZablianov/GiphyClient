package com.giphy.giphy.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkManager{

    const val TAG = "NetworkManager"
    const val BASE_URL = "api.giphy.com/v1/"

    val giphyGifApi: GiphyGifApi
    val gson: Gson

    init {
        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(AuthorizationInterceptor())
                .addInterceptor(ErrorResponseInterceptor())
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .build()
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()

        giphyGifApi = retrofit.create(GiphyGifApi::class.java)
        gson = GsonBuilder().create()
    }


}