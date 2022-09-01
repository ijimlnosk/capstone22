package com.daelim.capstone22

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class JoinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)
        val edtName = findViewById<EditText>(R.id.edt_name)
        val edtEmail = findViewById<EditText>(R.id.edt_email)
        val edtPw = findViewById<EditText>(R.id.edt_PW)
        val edtPwOk = findViewById<EditText>(R.id.edt_PWok)
    }
}