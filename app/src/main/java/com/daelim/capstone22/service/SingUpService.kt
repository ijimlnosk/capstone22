package com.daelim.capstone22

import com.daelim.capstone22.data.SignUpRequestBody
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SignUpService{
    @Headers("Content-Type: application/json")
    @POST("signup")
    fun addUserByEnqueue(@Body userInfo: SignUpRequestBody): retrofit2.Call<SignUpRequestBody>
}