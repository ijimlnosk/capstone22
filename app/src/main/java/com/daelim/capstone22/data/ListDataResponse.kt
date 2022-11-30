package com.daelim.capstone22.data

import com.google.gson.annotations.SerializedName

data class ListDataResponse(
    val result: ArrayList<ListData> ?= null,
    val totalOfDay : HashMap<Int,HashMap<String, Long>> ?= null,
    val plusMinus : HashMap<String,Long>? = null
)
