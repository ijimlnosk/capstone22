package com.daelim.capstone22.service

import com.daelim.capstone22.data.*
import retrofit2.Call
import retrofit2.http.*

interface ListService {

    /*@Headers("Content-Type: application/json")
    @GET("transactions")
    fun requestHeaderList(@Header("authorization") accessToken:String):Call<SignUpResponse>*/

    @Headers("Content-Type: application/json")
    @POST("transactions")
    fun requestBodyList(@Body listRequest: ListRequest) : Call<ListResponse>
}