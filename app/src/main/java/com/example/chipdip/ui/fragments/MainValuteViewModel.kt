package com.example.chipdip.ui.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.ViewModel
import com.example.chipdip.local.model.CurrencyEntity
import com.example.chipdip.local.model.CurrencyWithValue
import com.example.chipdip.local.model.ItemValueEntity
import com.example.chipdip.model.FullData
import com.example.chipdip.model.Valute
import com.example.chipdip.model.valute.ItemValute
import com.example.chipdip.model.valute.convert
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

    override fun collectList(data: Valute): MutableList<ItemValueEntity> {
        return mutableListOf(
            data.aUD.convert(), data.aZN.convert(), data.gBP.convert(), data.aMD.convert(),
            data.bYN.convert(), data.bGN.convert(), data.bRL.convert(), data.hUF.convert(),
            data.vND.convert(), data.hKD.convert(), data.gEL.convert(), data.dKK.convert(),
            data.aED.convert(), data.uSD.convert(), data.eUR.convert(), data.eGP.convert(),
            data.iNR.convert(), data.iDR.convert(), data.kZT.convert(), data.cAD.convert(),
            data.qAR.convert(), data.kGS.convert(), data.cNY.convert(), data.mDL.convert(),
            data.nZD.convert(), data.nOK.convert(), data.pLN.convert(), data.rON.convert(),
            data.xDR.convert(), data.sGD.convert(), data.tJS.convert(), data.tHB.convert(),
            data.tRY.convert(), data.tMT.convert(), data.uZS.convert(), data.uAH.convert(),
            data.cZK.convert(), data.sEK.convert(), data.cHF.convert(), data.rSD.convert(),
            data.zAR.convert(), data.kRW.convert(), data.jPY.convert()
        )
    }

    @SuppressLint("SimpleDateFormat")
    override fun getCurrencyEntity(data: FullData): CurrencyWithValue {
        val currentDate = SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(Date())
        val currencyEntity = CurrencyEntity(
            uid = 1,
            timestamp = currentDate,
            remoteTime = data.date,
//            valute = data.valute
        )
        val listItemValueEntity = collectList(data.valute)
        return CurrencyWithValue(
            currencyEntity = currencyEntity,
            listItemValueEntity = listItemValueEntity
        )
    }

}
