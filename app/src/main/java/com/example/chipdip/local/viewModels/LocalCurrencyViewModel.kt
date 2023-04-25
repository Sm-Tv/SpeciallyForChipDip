package com.example.chipdip.local.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.chipdip.local.CurrencyDataBase
import com.example.chipdip.local.CurrencyEntity
import com.example.chipdip.local.repository.LocalCurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocalCurrencyViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<CurrencyEntity>
    private val repository: LocalCurrencyRepository

    init {
        val currencyDao = CurrencyDataBase.getDatabase(application).modelDbDao()
        repository = LocalCurrencyRepository(currencyDao)
        readAllData = repository.readAllData
    }

    fun addFullCurrency(currencyEntity: CurrencyEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.addFullCurrency(currencyEntity)
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }

}
