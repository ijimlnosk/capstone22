package com.daelim.capstone22

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    val TAG: String = "LoginActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_userjoin.setOnClickListener {
            val intent = Intent(this,JoinActivity::class.java)
            startActivity(intent)
        }
        btn_userLogin.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        val tvInputName = tv_InputEmail.text.toString()
        val tbInputPw = tv_InputPw.text.toString()

        val sharedPreferences = getSharedPreferences("userInformation", MODE_PRIVATE)

        val saveName = sharedPreferences.getString("name","").toString()

        Log.d(TAG, saveName)

        if(tvInputName == ""){

            val intent = Intent(this,JoinActivity::class.java)
            startActivity(intent)

        }
        else{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            Toast.makeText(this,"로그인 완료",Toast.LENGTH_SHORT).show()
            finish()
        }

    }
}