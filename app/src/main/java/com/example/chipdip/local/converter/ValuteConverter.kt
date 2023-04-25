package com.example.chipdip.local.converter

import androidx.room.TypeConverter
import com.example.chipdip.model.Valute
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ValuteConverter {
//    @TypeConverter
//    fun fromValute(hobbies: ArrayList<LocalValute>): String {
//        var str = ""
//        hobbies.forEach {
//            val itemStr = "${it.name},${it.value},${it.previous},|"
//            str += itemStr
//        }
//        println(str)
//        return str
//    }
//
//    @TypeConverter
//    fun toValute(data: String): List<LocalValute> {
//        val valuteList: ArrayList<LocalValute> = arrayListOf()
//        val list3 = data.split("|").toTypedArray()
//        list3.forEach {
//            val localValueItems = it.split(",").toTypedArray()
//            val localValute = LocalValute(
//                name = localValueItems[0],
//                value = localValueItems[1].toDouble(),
//                previous = localValueItems[2].toDouble()
//            )
//            valuteList.add(localValute)
//        }
//        return valuteList
//    }

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
