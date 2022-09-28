package com.daelim.capstone22

import android.util.Log
import com.daelim.capstone22.data.SignUpRequestBodyDTO
import retrofit2.Call
import retrofit2.Response

class SignInWork(private val userInfo: SignUpRequestBodyDTO) {

    fun sign(){
        val service = SignUpAPI.emgMedService

        service.addUserByEnqueue(userInfo).enqueue(object : retrofit2.Callback<SignUpRequestBodyDTO>{
            override fun onResponse(
                call: Call<SignUpRequestBodyDTO>,
                response: Response<SignUpRequestBodyDTO>
            ) {
                if (response.isSuccessful){
                    val result = response.body()
                    Log.d("회원가입 성공","$result")
                }
            }

            override fun onFailure(call: Call<SignUpRequestBodyDTO>, t: Throwable) {
                Log.d("회원가입 실패",t.message.toString())
            }
        })
    }
}