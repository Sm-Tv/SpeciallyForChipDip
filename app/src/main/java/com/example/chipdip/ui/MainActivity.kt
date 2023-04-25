package com.example.chipdip.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.chipdip.databinding.ActivityMainBinding
import com.example.chipdip.local.CurrencyEntity
import com.example.chipdip.local.viewModels.LocalCurrencyViewModel
import com.example.chipdip.model.FullData
import com.example.chipdip.remote.viewModel.RemoteCurrencyViewModel
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModelLocal: LocalCurrencyViewModel by viewModels()
    private val viewModelRemote: RemoteCurrencyViewModel by viewModels()

    private var localValute: CurrencyEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindingRemoteValute()
        bindingLocalValute()
        initClick()
    }

    private fun bindingRemoteValute() {
        viewModelRemote.fullData.observe(this) { response ->
            if (response.isSuccessful) {
                val str = response.body()
                binding.textResponse.text = str?.toString()
                str?.let { addDataLocalDatabase(str) }
            } else {
                Toast.makeText(this, "ERROR connect ${response.errorBody().toString()}", Toast.LENGTH_SHORT).show()
                Toast.makeText(this, "Показанны данные из бд", Toast.LENGTH_SHORT).show()
                binding.textResponse.text = localValute.toString()
            }
        }
    }

    private fun bindingLocalValute() {
        viewModelLocal.readAllData.observe(this) { currency ->
            localValute = currency
        }
    }


    // можно вынести во viewModel
    @SuppressLint("SimpleDateFormat")
    private fun addDataLocalDatabase(data: FullData) {
        val currentDate = SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(Date())
        val fullCurrency = CurrencyEntity(
            uid = 1,
            timestamp = currentDate,
            remoteTime = data.timestamp,
            valute = data.valute
        )
        viewModelLocal.addFullCurrency(fullCurrency)
    }


    private fun initClick() {
        binding.startButton.setOnClickListener {
            viewModelRemote.getValute()
        }
    }
}
