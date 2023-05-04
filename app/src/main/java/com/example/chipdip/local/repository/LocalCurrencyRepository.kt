package com.example.chipdip.local.repository

import androidx.lifecycle.LiveData
import com.example.chipdip.local.CurrencyDao
import com.example.chipdip.local.CurrencyDaoo
import com.example.chipdip.local.model.CurrencyEntity
import com.example.chipdip.local.model.CurrencyWithValue
import com.example.chipdip.local.model.ItemValueEntity

class LocalCurrencyRepository(private val currencyDao: CurrencyDao, private val currencyDaoo: CurrencyDaoo,) {

    val readAllData: LiveData<CurrencyWithValue> = currencyDao.getAllLiveData()

    fun addFullCurrency(currencyWithValue: CurrencyWithValue) {
        currencyDaoo.insert(currencyWithValue)
    }

//    fun addFullCurrencyParent(currencyEntity: CurrencyEntity) {
//        currencyDao.insert(currencyEntity)
//    }
//    fun addFullCurrencyChilde(itemValueEntity: List<ItemValueEntity>) {
//        currencyDao.insert(itemValueEntity)
//    }

    fun deleteAll() {
        currencyDao.deleteAllCurrency()
        currencyDao.deleteAllItemValue()
    }
}
