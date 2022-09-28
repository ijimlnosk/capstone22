package com.daelim.capstone22.service

import com.daelim.capstone22.data.SignInRequestBodyDTO
import retrofit2.Call
import retrofit2.http.*

interface SignInService {

        @FormUrlEncoded
        @POST("/signin")
        fun requestSignIn(
                @Field("email") email:String,
                @Field("password") password:String
        ) : Call<SignInRequestBodyDTO>

        /*@Headers("Content-Type: application/json")
        @POST("signin")
        fun addUserByEnqueue(@Body userInfo: SignInRequestBodyDTO): retrofit2.Call<SignInRequestBodyDTO>*/ // call 은 흐름처리 기능을 제공
}