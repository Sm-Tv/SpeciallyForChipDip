package com.example.chipdip.ui.fragments

import android.content.Context
import com.example.chipdip.local.CurrencyEntity
import com.example.chipdip.model.FullData
import com.example.chipdip.model.Valute
import com.example.chipdip.model.valute.ItemValute

interface MainValuteViewModelInterface {

    fun checkNetwork(context: Context): Boolean

    fun collectList(data: Valute): MutableList<ItemValute>

    fun getCurrencyEntity(data: FullData): CurrencyEntity
}
