package com.daelim.capstone22

import com.daelim.capstone22.service.SignUpService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SignUpAPI {
    private const val BASE_URL="https://localhost:8080"

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).
        addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)//로그캣에서 패치 내용을 모니터링 가능능
           .build()
    }
    val emgMedService: SignUpService by lazy {
        retrofit.create(SignUpService::class.java)
    }
}