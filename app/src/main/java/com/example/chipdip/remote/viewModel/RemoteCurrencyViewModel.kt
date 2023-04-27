package com.example.chipdip.remote.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chipdip.model.FullData
import com.example.chipdip.remote.repository.RemoteCurrencyRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class RemoteCurrencyViewModel: ViewModel() {

    val fullData: MutableLiveData<Response<FullData>> = MutableLiveData()
    private val repository: RemoteCurrencyRepository = RemoteCurrencyRepository()

    fun getValute() {
        viewModelScope.launch {
            val repository = repository.getValute()
            fullData.value = repository
        }
    }

}
