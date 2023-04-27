package com.example.chipdip.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val BASE_URL = "https://www.cbr-xml-daily.ru"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: RemoteApi by lazy {
        retrofit.create(RemoteApi::class.java)
    }

}
