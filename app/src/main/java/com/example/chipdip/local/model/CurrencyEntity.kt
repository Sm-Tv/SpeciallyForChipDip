package com.example.chipdip.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Currency")
data class CurrencyEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "uid")
    val uid: Long = 0,

    @ColumnInfo(name = "timeStamp")
    val timestamp: String,

    @ColumnInfo(name = "remoteTime")
    val remoteTime: String,

//    @ColumnInfo(name = "valute")
//    val valute: ItemValueEntity,

)
