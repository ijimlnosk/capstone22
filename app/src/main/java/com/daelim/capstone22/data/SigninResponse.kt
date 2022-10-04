package com.daelim.capstone22.data

import com.google.gson.annotations.SerializedName

data class SigninResponse(
    //@SerializedName("name") val name:String,
    @SerializedName("email") val email:String,
    @SerializedName("password") val password:String
)
