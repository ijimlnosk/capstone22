package com.daelim.capstone22

import android.content.Context
import android.content.Context.MODE_PRIVATE

class TokenPrefs(context: Context) {
    private val prefNum="jwt"
    private val prefs= context.getSharedPreferences(prefNum,MODE_PRIVATE)

    var token:String?
    get() = prefs.getString("token",null)
    set(value){
        prefs.edit().putString("token",value).apply()
    }
}