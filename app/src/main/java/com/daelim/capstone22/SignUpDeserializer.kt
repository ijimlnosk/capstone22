/*
package com.daelim.capstone22

import com.daelim.capstone22.data.SignUpData
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.util.*

class SignUpDeserializer : JsonDeserializer<SignUpData> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): SignUpData {
        val jsonObject = json?.asJsonObject ?: throw NullPointerException("Response Json String is null")

        val name = jsonObject["name"].asString
        val date : Date = Date(jsonObject["create_at"].asString.toLong()*1000)
        val email = jsonObject["email"].asString
        val password = jsonObject["password"].asString

        return SignUpData(name,date,email,password)
    }
}*/
