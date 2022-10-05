package com.daelim.capstone22.service

import android.content.SharedPreferences
import com.daelim.capstone22.data.ListRequest
import com.daelim.capstone22.data.ListResponse
import com.daelim.capstone22.data.SignInRequestBodyDTO
import com.daelim.capstone22.data.SigninResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ListService {

    @Headers("Content-Type: application/json")
    @POST("transaction")
    fun requestList(@Body listRequest: ListRequest) : Call<ListResponse>


}