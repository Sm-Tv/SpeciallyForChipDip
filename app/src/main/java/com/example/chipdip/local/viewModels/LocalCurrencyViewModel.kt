package com.example.chipdip.local.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.chipdip.local.CurrencyDataBase
import com.example.chipdip.local.model.CurrencyEntity
import com.example.chipdip.local.model.CurrencyWithValue
import com.example.chipdip.local.repository.LocalCurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocalCurrencyViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<CurrencyWithValue>
    private val repository: LocalCurrencyRepository

    init {
        val currencyDao = CurrencyDataBase.getDatabase(application).modelDbDao()
        val currencyDaoo = CurrencyDataBase.getDatabase(application).modelDbDaoo()
        repository = LocalCurrencyRepository(currencyDao, currencyDaoo)
        readAllData = repository.readAllData
    }

    fun addFullCurrency(currencyWithValue: CurrencyWithValue) = viewModelScope.launch(Dispatchers.IO) {
        repository.addFullCurrency(currencyWithValue)
    }
//    fun addFullParent(currencyWithValue: CurrencyWithValue) = viewModelScope.launch(Dispatchers.IO) {
//        repository.addFullCurrency(currencyWithValue)
//    }
//    fun addFullChild(currencyWithValue: CurrencyWithValue) = viewModelScope.launch(Dispatchers.IO) {
//        repository.addFullCurrency(currencyWithValue)
//    }


    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }

}
