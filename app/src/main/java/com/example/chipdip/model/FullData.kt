package com.example.chipdip.model

import com.google.gson.annotations.SerializedName

data class FullData(
    @SerializedName("Date") val date: String,
    @SerializedName("PreviousDate") val previousDate: String,
    @SerializedName("PreviousURL") val previousURL: String,
    @SerializedName("Timestamp") val timestamp: String,
    @SerializedName("Valute") val valute: Valute
)
