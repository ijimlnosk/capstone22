package com.daelim.capstone22.data

import com.google.gson.annotations.SerializedName
import java.util.*

data class SignUpResponse(
    @SerializedName("email")
    var email: String,
    /*@SerializedName("create_at")
    var create_at: Date,*/
    @SerializedName("name")
    var name: String,
    @SerializedName("password")
    var password: String
)
