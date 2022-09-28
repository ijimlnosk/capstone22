package com.daelim.capstone22

import com.daelim.capstone22.data.SignUpRequestBodyDTO
import retrofit2.http.POST

interface SignUpInterAPI {

    @POST("/signin")
    suspend fun postUserList() : List<SignUpRequestBodyDTO>

}