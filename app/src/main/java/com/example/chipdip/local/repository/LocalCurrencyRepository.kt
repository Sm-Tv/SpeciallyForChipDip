package com.example.chipdip.local.repository

import androidx.lifecycle.LiveData
import com.example.chipdip.local.CurrencyDao
import com.example.chipdip.local.CurrencyEntity

class LocalCurrencyRepository(private val currencyDao: CurrencyDao) {

    val readAllData: LiveData<CurrencyEntity> = currencyDao.getAllLiveData()

    fun addFullCurrency(currencyEntity: CurrencyEntity) {
        currencyDao.insert(currencyEntity)
    }

    fun deleteAll() {
        currencyDao.deleteAllNotes()
    }
}
