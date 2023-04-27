package com.example.chipdip.local.repository

import androidx.lifecycle.LiveData
import com.example.chipdip.local.CurrencyDao
import com.example.chipdip.local.CurrencyEntity

class LocalCurrencyRepository(private val currencyDao: CurrencyDao) {

    val readAllData: LiveData<List<CurrencyEntity>> = currencyDao.getAllLiveData()
    val readLastDateData: LiveData<CurrencyEntity> = currencyDao.getLastDateLiveData()

    fun addFullCurrency(currencyEntity: CurrencyEntity) {
        currencyDao.insert(currencyEntity)
    }

    fun deleteAll() {
        currencyDao.deleteAllNotes()
    }
}
