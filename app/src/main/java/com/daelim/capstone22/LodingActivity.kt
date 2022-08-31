package com.daelim.capstone22

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class LodingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loding)
        startLoding()
    }

    private fun startLoding(){
        val handler = Handler()
        handler.postDelayed({ finish() },2000)
    }
}