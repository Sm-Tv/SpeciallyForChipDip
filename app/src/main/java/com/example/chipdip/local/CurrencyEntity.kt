package com.example.chipdip.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.chipdip.local.converter.ValuteConverter
import com.example.chipdip.model.Valute

@Entity(tableName = "Currency")
data class CurrencyEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "uid")
    val uid: Int,

    @ColumnInfo(name = "timeStamp")
    val timestamp: String,

    @ColumnInfo(name = "valute")
    val valute: Valute
)


//data class LocalValute(
//    val name: String,
//    val value: Double,
//    val previous: Double,
//)
