package com.daelim.capstone22.data

import com.google.gson.annotations.SerializedName

data class SigninResponse(
    @SerializedName("token") val token:String,
    @SerializedName("result") val result:String
)
