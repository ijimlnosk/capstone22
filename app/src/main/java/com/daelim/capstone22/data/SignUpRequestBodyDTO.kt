package com.daelim.capstone22.data

import com.google.gson.annotations.SerializedName
import java.util.*

data class SignUpRequestBodyDTO(
    @SerializedName("email")
    val email:String,
    @SerializedName("password")
    val password:String
)
