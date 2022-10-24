package com.daelim.capstone22.data

import com.google.gson.annotations.SerializedName

data class ListResponse(
    @SerializedName("message") val message:String,
    @SerializedName("result") val result:String
)
