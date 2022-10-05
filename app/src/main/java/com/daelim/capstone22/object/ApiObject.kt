package com.daelim.capstone22.`object`

import com.daelim.capstone22.service.SignInService
import com.daelim.capstone22.service.SignUpService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiObject {

    private const val BASE_URL = "http://192.168.17.1:8080"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val signInService: SignInService = retrofit.create(SignInService::class.java)
    val signUpService: SignUpService = retrofit.create(SignUpService::class.java)

    /*private const val BASE_URL="http://172.16.1.161:8080"

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
    val emgMedService: SignInService by lazy {
        retrofit.create(SignInService::class.java)
    }*/
}