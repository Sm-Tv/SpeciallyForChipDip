package com.example.chipdip.model.valute

import com.example.chipdip.local.model.ItemValueEntity
import com.google.gson.annotations.SerializedName

data class ItemValute(
    @SerializedName("ID") val iD: String,
    @SerializedName("NumCode") val numCode: String,
    @SerializedName("CharCode") val charCode: String,
    @SerializedName("Nominal") val nominal: Int,
    @SerializedName("Name") val name: String,
    @SerializedName("Value") val value: Double,
    @SerializedName("Previous") val previous: Double

)

fun ItemValute.convert(): ItemValueEntity{
    return ItemValueEntity(
        iD = iD,
        numCode = numCode,
        charCode = charCode,
        nominal = nominal,
        name = name,
        value = value,
        previous = previous
    )
}
