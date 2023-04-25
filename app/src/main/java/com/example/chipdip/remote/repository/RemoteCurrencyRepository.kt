package com.example.chipdip.remote.repository

import com.example.chipdip.model.FullData
import com.example.chipdip.remote.RetrofitInstance
import retrofit2.Response

class RemoteCurrencyRepository {

    suspend fun getValute(): Response<FullData> {
        return RetrofitInstance.api.getValute()
    }

}
