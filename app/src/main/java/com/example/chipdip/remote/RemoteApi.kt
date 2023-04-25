package com.example.chipdip.remote

import com.example.chipdip.model.FullData
import retrofit2.Response
import retrofit2.http.GET

interface RemoteApi {

    @GET("./daily_json.js")
    suspend fun getValute(): Response<FullData>

}
