package com.example.chipdip.model.valute

import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.chipdip.local.CurrencyEntity
import com.google.gson.annotations.SerializedName


//@Entity(foreignKeys = [ForeignKey(entity = CurrencyEntity::class,
//    parentColumns = arrayOf("uid"),
//    childColumns = arrayOf("iD"),
//    onDelete = ForeignKey.CASCADE)]
//)

data class ItemValute(
    @SerializedName("ID") val iD: String,
    @SerializedName("NumCode") val numCode: String,
    @SerializedName("CharCode") val charCode: String,
    @SerializedName("Nominal") val nominal: Int,
    @SerializedName("Name") val name: String,
    @SerializedName("Value") val value: Double,
    @SerializedName("Previous") val previous: Double
)
