/*
package com.daelim.capstone22

import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.daelim.capstone22.`object`.SignInAPI
import com.daelim.capstone22.data.SignInRequestBodyDTO
import retrofit2.Call
import retrofit2.Response

class SignInWork(private val userInfo: SignInRequestBodyDTO) {

    fun sign(){
        val service = SignInAPI.emgMedService

        service.addUserByEnqueue(userInfo).enqueue(object : retrofit2.Callback<SignInRequestBodyDTO>{
            override fun onResponse(
                call: Call<SignInRequestBodyDTO>,
                response: Response<SignInRequestBodyDTO>
            ) {
                if (response.isSuccessful){
                    val result = response.body()
                    Log.d("로그인 성공","$result")
                }
            }

            override fun onFailure(call: Call<SignInRequestBodyDTO>, t: Throwable) {
                Log.d("로그인 실패",t.message.toString())
            }
        })
    }
}
*/
