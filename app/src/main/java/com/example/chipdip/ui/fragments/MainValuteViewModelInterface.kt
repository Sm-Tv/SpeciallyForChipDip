package com.example.chipdip.ui.fragments

import android.content.Context
import com.example.chipdip.local.model.CurrencyEntity
import com.example.chipdip.local.model.CurrencyWithValue
import com.example.chipdip.local.model.ItemValueEntity
import com.example.chipdip.model.FullData
import com.example.chipdip.model.Valute
import com.example.chipdip.model.valute.ItemValute

interface MainValuteViewModelInterface {

    fun checkNetwork(context: Context): Boolean

    fun collectList(data: Valute): MutableList<ItemValueEntity>

    fun getCurrencyEntity(data: FullData): CurrencyWithValue

}
