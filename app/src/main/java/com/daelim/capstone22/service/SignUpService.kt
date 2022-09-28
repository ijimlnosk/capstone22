package com.daelim.capstone22.service

import com.daelim.capstone22.data.SignUpRequestBodyDTO
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SignUpService {
        @Headers("Content-Type: application/json")
        @POST("signup")
        fun addUserByEnqueue(@Body userInfo: SignUpRequestBodyDTO): retrofit2.Call<SignUpRequestBodyDTO> // call 은 흐름처리 기능을 제공
}