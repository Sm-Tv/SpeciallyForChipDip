package com.example.chipdip.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.chipdip.databinding.FragmentAllValuteBinding
import com.example.chipdip.local.CurrencyEntity
import com.example.chipdip.local.viewModels.LocalCurrencyViewModel
import com.example.chipdip.model.FullData
import com.example.chipdip.model.Valute
import com.example.chipdip.model.valute.ItemValute
import com.example.chipdip.remote.viewModel.RemoteCurrencyViewModel
import com.example.chipdip.ui.adapters.ValuteAdapter
import java.text.SimpleDateFormat
import java.util.*

class AllValuteFragment : Fragment() {

    private lateinit var binding: FragmentAllValuteBinding

    private val viewModelLocal: LocalCurrencyViewModel by viewModels()
    private val viewModelRemote: RemoteCurrencyViewModel by viewModels()
    private val viewModelMainFragment: MainValuteViewModel by viewModels()
    private var localValute: CurrencyEntity? = null
    private lateinit var adapter: ValuteAdapter
    private var items: MutableList<ItemValute> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAllValuteBinding.inflate(layoutInflater)
        bindingRemoteValute()
        bindingLocalValute()
        initClick()
        initial()
        return binding.root
    }

    private fun initial() {
        binding.recyclerViewValue.layoutManager = GridLayoutManager(requireContext(), 1)
        binding.recyclerViewValue.addItemDecoration(
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        )
        adapter = ValuteAdapter()
        binding.recyclerViewValue.adapter = adapter
    }

    private fun bindingRemoteValute() {
        viewModelRemote.fullData.observe(viewLifecycleOwner) { response ->
            if (response.isSuccessful) {
                val str = response.body()
                str?.let { items = collectList(it.valute) }
                str?.let { addDataLocalDatabase(str) }
            } else {
                if (localValute != null) {
//                    binding.startButton.text = "Обновить"
                    localValute?.let { items = collectList(it.valute) }
                }
                Toast.makeText(requireContext(), "ERROR connect! Code error ${response.code()} ", Toast.LENGTH_SHORT)
                    .show()
                Toast.makeText(requireContext(), "Показанны данные из бд", Toast.LENGTH_SHORT).show()
            }
            adapter.setData(items)
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

    private fun collectList(data: Valute): MutableList<ItemValute> {
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

    private fun initClick() {
        binding.startButton.setOnClickListener {
            if (viewModelMainFragment.checkNetwork(requireContext())) viewModelRemote.getValute()
            else {
                if (localValute != null) localValute?.let { adapter.setData(collectList(it.valute)) }
                else Toast.makeText(requireContext(), "БД пустая и нет соединения с интернетом", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

}
