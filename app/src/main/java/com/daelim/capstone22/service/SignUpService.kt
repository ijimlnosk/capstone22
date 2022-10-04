package com.daelim.capstone22.service

import com.daelim.capstone22.data.SignInRequestBodyDTO
import com.daelim.capstone22.data.SignUpRequest
import com.daelim.capstone22.data.SignUpResponse
import retrofit2.Call
import retrofit2.http.*

interface SignUpService {

    @POST("members/signup")
    @Headers("accept: application/json","content-Type: application/json")
    fun requestSignUp(@Body signUpRequest: SignUpRequest) : Call<SignUpResponse>

}