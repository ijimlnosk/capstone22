package com.daelim.capstone22.service

import com.daelim.capstone22.data.CalendarDataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface CalendarService {

    @Headers("Content-Type: application/json")
    @GET("transactions/{year}/{month}")
    fun getTran(@Path("year") year: String,
                @Path("month") month: String,
                @QueryMap par: Map<String,String>): Call<CalendarDataResponse>

}