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
            data.aUD, data.aZN, data.gBP, data.aMD, data.bYN, data.bGN, data.bRL,
            data.hUF, data.vND, data.hKD, data.gEL, data.dKK, data.aED, data.uSD,
            data.eUR, data.eGP, data.iNR, data.iDR, data.kZT, data.cAD, data.qAR,
            data.kGS, data.cNY, data.mDL, data.nZD, data.nOK, data.pLN, data.rON,
            data.xDR, data.sGD, data.tJS, data.tHB, data.tRY, data.tMT, data.uZS,
            data.uAH, data.cZK, data.sEK, data.cHF, data.rSD, data.zAR, data.kRW,
            data.jPY
        )
    }

    @SuppressLint("SimpleDateFormat")
    override fun getCurrencyEntity(data: FullData): CurrencyEntity {
        val currentDate = SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(Date())
        val date = Date().time
        return CurrencyEntity(
            uid = 1,
            timestamp = currentDate,
            date = date,
            remoteTime = data.date,
            valute = data.valute
        )
    }

}
