package com.example.chipdip.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.chipdip.model.Valute

@Entity(tableName = "Currency")
data class CurrencyEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid")
    val uid: Int,

    @ColumnInfo(name = "date")
    val date: Long,

    @ColumnInfo(name = "timeStamp")
    val timestamp: String,

    @ColumnInfo(name = "remoteTime")
    val remoteTime: String,

    @ColumnInfo(name = "valute")
    val valute: Valute,

)
