package com.daelim.capstone22.`object`

import com.daelim.capstone22.AuthInterceptor
import com.daelim.capstone22.data.ListDataResponse
import com.daelim.capstone22.service.CalendarService
import com.daelim.capstone22.service.ListService
import com.daelim.capstone22.service.SignInService
import com.daelim.capstone22.service.SignUpService
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create

object ApiObject {
    private const val BASE_URL = "http://192.168.123.103:8080"

    val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .client(provideokHttpClient(AuthInterceptor()))
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    private fun provideokHttpClient(interceptor: AuthInterceptor) : OkHttpClient = OkHttpClient.Builder().run {
        addInterceptor(interceptor)
        build()
    }


    val signInService: SignInService = retrofit.create(SignInService::class.java)
    val signUpService: SignUpService = retrofit.create(SignUpService::class.java)
    //val listService: ListService = retrofit.create(ListService::class.java)
    fun listService() : ListService {
        return retrofit.create(ListService::class.java)
    }
    fun calendarService() : CalendarService{
        return retrofit.create(CalendarService::class.java)
    }

}
