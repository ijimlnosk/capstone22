/*
package com.daelim.capstone22

import com.daelim.capstone22.data.SignUpData
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {

    private val gson: Gson by lazy {
        GsonBuilder().registerTypeAdapter(SignUpData::class.java,SignUpDeserializer()).create()
    }

    private val retrofit : Retrofit by lazy {
        Retrofit
            .Builder()
            .baseUrl("localhost:8080/signup")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
    val signUpAPI : SignUpAPI by lazy {
        retrofit.create(SignUpAPI::class.java)
    }

}*/
