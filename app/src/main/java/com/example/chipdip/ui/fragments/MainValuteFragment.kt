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
import com.example.chipdip.model.valute.ItemValute
import com.example.chipdip.remote.viewModel.RemoteCurrencyViewModel
import com.example.chipdip.ui.adapters.ValuteAdapter

class MainValuteFragment : Fragment() {

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

    @SuppressLint("SetTextI18n")
    private fun bindingRemoteValute() {
        viewModelRemote.fullData.observe(viewLifecycleOwner) { response ->
            if (response.isSuccessful) {
                val str = response.body()
                str?.let {
                    binding.dataText.text = "Показан курс валют за: ${it.date}"
                    items = viewModelMainFragment.collectList(it.valute)
                    viewModelLocal.addFullCurrency(viewModelMainFragment.getCurrencyEntity(str))
                }
            } else {
                if (localValute != null) {
                    localValute?.let {
                        items = viewModelMainFragment.collectList(it.valute)
                        binding.dataText.text = "Показан курс валют за: ${it.timestamp}"
                    }
                }
                Toast.makeText(requireContext(), "ERROR connect! Code error ${response.code()} ", Toast.LENGTH_SHORT)
                    .show()
                Toast.makeText(requireContext(), "Показанны данные из БД", Toast.LENGTH_SHORT).show()
            }
            adapter.setData(items)
        }
    }

    private fun bindingLocalValute() {
        viewModelLocal.readAllData.observe(viewLifecycleOwner) { currency ->
            localValute = currency
            getData()
        }
    }

    private fun initClick() {
        binding.startButton.setOnClickListener {
            getData()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun getData() {
        if (viewModelMainFragment.checkNetwork(requireContext())) viewModelRemote.getValute()
        else {
            Toast.makeText(requireContext(), "Нет подключения к интернету!", Toast.LENGTH_SHORT).show()
            if (localValute != null) {
                localValute?.let {
                    binding.dataText.text = "Показан курс валют за: ${it.timestamp}"
                    adapter.setData(viewModelMainFragment.collectList(it.valute))
                }
            } else Toast.makeText(requireContext(), "БД пустая и нет соединения с интернетом", Toast.LENGTH_SHORT)
                .show()
        }
    }

}
