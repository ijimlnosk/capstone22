package com.daelim.capstone22.data

import com.google.gson.annotations.SerializedName

data class ListRequest(
    @SerializedName("name") val name:String,
    @SerializedName("amount") val amount:String,
    @SerializedName("categoryType") val categoryType:String,
    @SerializedName("transactionType") val transactionType:String
)
