package com.daelim.capstone22.service

import android.provider.ContactsContract
import com.daelim.capstone22.data.SignInRequestBodyDTO
import com.daelim.capstone22.data.SigninResponse
import retrofit2.Call
import retrofit2.http.*

interface SignInService {

        //@FormUrlEncoded
        @Headers("Content-Type: application/json")
        @POST("members/signin")
        fun requestSignIn(@Body signInRequestBodyDTO: SignInRequestBodyDTO) : Call<SigninResponse>

        /*@Headers("Content-Type: application/json")
        @POST("signin")
        fun addUserByEnqueue(@Body userInfo: SignInRequestBodyDTO): retrofit2.Call<SignInRequestBodyDTO>*/ // call 은 흐름처리 기능을 제공
}