package com.daelim.capstone22.data

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime
import java.util.*

data class SignUpRequest(
    @SerializedName("email")
    val email:String,
    /*@SerializedName("create_at")
    val create_at: LocalDateTime,*/
    @SerializedName("password")
    val password:String,
    @SerializedName("name")
    val name:String
)
