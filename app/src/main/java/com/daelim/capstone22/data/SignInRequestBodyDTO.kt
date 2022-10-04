package com.daelim.capstone22.data

import com.google.gson.annotations.SerializedName
import java.util.*

data class SignInRequestBodyDTO(
    /*val email: String,
    val password: String,*/
    /*@SerializedName("name")
    val name: String,*/
    @SerializedName("email")
    val email:String,
    @SerializedName("password")
    val password:String
)
