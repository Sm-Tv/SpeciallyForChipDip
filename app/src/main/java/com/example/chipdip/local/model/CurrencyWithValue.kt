package com.example.chipdip.local.model

import androidx.room.Embedded
import androidx.room.Relation

data class CurrencyWithValue(
    @Embedded
    val currencyEntity: CurrencyEntity,
    @Relation(parentColumn = "uid", entity = ItemValueEntity::class, entityColumn = "uidChild")
    val listItemValueEntity: List <ItemValueEntity>,
)
