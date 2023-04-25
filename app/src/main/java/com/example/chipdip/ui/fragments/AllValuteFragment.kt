package com.example.chipdip.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.chipdip.databinding.FragmentAllValuteBinding
import com.example.chipdip.local.CurrencyEntity
import com.example.chipdip.local.viewModels.LocalCurrencyViewModel
import com.example.chipdip.model.FullData
import com.example.chipdip.remote.viewModel.RemoteCurrencyViewModel
import java.text.SimpleDateFormat
import java.util.*

class AllValuteFragment : Fragment() {

    private lateinit var binding: FragmentAllValuteBinding

    private val viewModelLocal: LocalCurrencyViewModel by viewModels()
    private val viewModelRemote: RemoteCurrencyViewModel by viewModels()
    private var localValute: CurrencyEntity? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAllValuteBinding.inflate(layoutInflater)
        bindingRemoteValute()
        bindingLocalValute()
        initClick()
        return binding.root
    }


    private fun bindingRemoteValute() {
        viewModelRemote.fullData.observe(viewLifecycleOwner) { response ->
            if (response.isSuccessful) {
                val str = response.body()
//                binding.textResponse.text = str?.date.toString() + response.code().toString()
                str?.let { addDataLocalDatabase(str) }
            } else {
                Toast.makeText(requireContext(), "ERROR connect! Code error ${response.code()} ", Toast.LENGTH_SHORT).show()
                Toast.makeText(requireContext(), "Показанны данные из бд", Toast.LENGTH_SHORT).show()
//                binding.textResponse.text = localValute.toString()
            }
        }
    }

    private fun bindingLocalValute() {
        viewModelLocal.readAllData.observe(viewLifecycleOwner) { currency ->
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
            remoteTime = data.date,
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
