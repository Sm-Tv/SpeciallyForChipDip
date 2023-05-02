package com.example.chipdip.ui.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.ViewModel
import com.example.chipdip.local.CurrencyEntity
import com.example.chipdip.model.FullData
import com.example.chipdip.model.Valute
import com.example.chipdip.model.valute.ItemValute
import java.text.SimpleDateFormat
import java.util.*

class MainValuteViewModel : ViewModel(), MainValuteViewModelInterface {

    override fun checkNetwork(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION")
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }

    override fun collectList(data: Valute): MutableList<ItemValute> {
        return mutableListOf(
            data.AUD, data.AZN, data.GBP, data.AMD, data.BYN, data.BGN, data.BRL,
            data.HUF, data.VND, data.HKD, data.GEL, data.DKK, data.AED, data.USD,
            data.EUR, data.EGP, data.INR, data.IDR, data.KZT, data.CAD, data.QAR,
            data.KGS, data.CNY, data.MDL, data.NZD, data.NOK, data.PLN, data.RON,
            data.XDR, data.SGD, data.TJS, data.THB, data.TRY, data.TMT, data.UZS,
            data.UAH, data.CZK, data.SEK, data.CHF, data.RSD, data.ZAR, data.KRW,
            data.JPY
        )
    }

    @SuppressLint("SimpleDateFormat")
    override fun getCurrencyEntity(data: FullData): CurrencyEntity {
        val currentDate = SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(Date())
        val date = Date().time
        return CurrencyEntity(
            uid = 0,
            timestamp = currentDate,
            date = date,
            remoteTime = data.date,
            valute = data.valute
        )
    }

}
