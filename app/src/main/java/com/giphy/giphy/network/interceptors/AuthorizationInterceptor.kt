package com.giphy.giphy.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response

val API_KEY = "pH3A03S4lHZ5RDp7B28RWFVNKmXhzY4A"

class AuthorizationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
                .newBuilder()
                .url(chain.request()
                        .url()
                        .newBuilder()
                        .addQueryParameter("api_key", API_KEY)
                        .build())
                .build()
        return chain.proceed(request)
    }
}