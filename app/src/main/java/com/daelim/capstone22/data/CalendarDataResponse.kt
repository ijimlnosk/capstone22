package com.daelim.capstone22.data

data class CalendarDataResponse(
    val result : ArrayList<CalendarData>? =null,
    val totalOfDay : HashMap<Int,HashMap<String,Long>> ?= null,
    val plusMinus : HashMap<String,Long>? = null
)
