package com.daelim.capstone22

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object RetrofitAPI {
    private const val BASE_URL="localhost:8080/signup"

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build()
    }
}