package com.daelim.capstone22.data

import com.google.gson.annotations.SerializedName

data class SignUpRequestBody(
    @SerializedName("result")
    val emil:String?,
    @SerializedName("status")
    val password:String?
)
