package com.example.chipdip.local.converter

import androidx.room.TypeConverter
import com.example.chipdip.model.Valute
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ValuteConverter {

    @TypeConverter
    fun toValute(json: String): Valute {
        val type = object : TypeToken<Valute>() {}.type
        return Gson().fromJson(json, type)
    }

    @TypeConverter
    fun toJson(valute: Valute): String {
        val type = object : TypeToken<Valute>() {}.type
        return Gson().toJson(valute, type)
    }

}
