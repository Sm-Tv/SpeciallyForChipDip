package com.example.chipdip.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    foreignKeys = [ForeignKey(
        entity = CurrencyEntity::class,
        parentColumns = arrayOf("uid"),
        childColumns = arrayOf("uidChild"),
        onDelete = ForeignKey.CASCADE
    )],
    tableName = "itemValue",
//    primaryKeys = ["uidChild", "uidParent"]
)
data class ItemValueEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uidChild")
    val uidChild: Long = 0,
//    @ColumnInfo(name = "uidParent")
//    val uidParent: Long = 0,
    @ColumnInfo(name = "iD")
    val iD: String,
    @ColumnInfo(name = "numCode")
    val numCode: String,
    @ColumnInfo(name = "charCode")
    val charCode: String,
    @ColumnInfo(name = "nominal")
    val nominal: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "value")
    val value: Double,
    @ColumnInfo(name = "previous")
    val previous: Double
)
