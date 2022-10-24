package com.daelim.capstone22

import android.app.Application

class App:Application() {

    companion object{
        lateinit var prefs: TokenPrefs
    }

    override fun onCreate() {
        prefs= TokenPrefs(applicationContext)
        super.onCreate()
    }
}