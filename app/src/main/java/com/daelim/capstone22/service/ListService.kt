package com.daelim.capstone22.service

import android.view.SurfaceControl
import com.daelim.capstone22.data.*
import retrofit2.Call
import retrofit2.http.*
import java.time.LocalDate
import java.time.Year
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

interface ListService {

    @Headers("Content-Type: application/json")
    @GET("transactions/{year}/{month}")
    fun getTran(@Path("year") year: String,
                @Path("month") month: String,
                @QueryMap par: Map<String,String>): Call<ListDataResponse>

}