package com.daelim.capstone22

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_join.view.*
import kotlinx.android.synthetic.main.activity_login.*

class LodingActivity : AppCompatActivity() {

    val TAG: String = "LodingActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loding)
    }
}