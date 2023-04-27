package com.example.chipdip.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.chipdip.R
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
        adapter = ValuteAdapter(requireContext())
        binding.recyclerViewValue.adapter = adapter
    }

    private fun bindingRemoteValute() {
        viewModelRemote.fullData.observe(viewLifecycleOwner) { response ->
            if (response.isSuccessful) {
                val str = response.body()
                str?.let {
                    val split = it.date.split("T")
                    binding.dataText.text =
                        String.format(resources.getString(R.string.data_remote_valute_rate), split[0], split[1])
                    items = viewModelMainFragment.collectList(it.valute)
                    viewModelLocal.addFullCurrency(viewModelMainFragment.getCurrencyEntity(str))
                }
            } else {
                if (localValute != null) {
                    localValute?.let {
                        items = viewModelMainFragment.collectList(it.valute)
                        binding.dataText.text =
                            String.format(resources.getString(R.string.data_local_valute_rate), it.timestamp)
                    }
                }
                showToastShort(String.format(resources.getString(R.string.error_network_connect)))
                showToastShort(resources.getString(R.string.show_data_BD))
            }
            adapter.setData(items)
            showProgressBar(false)
        }
    }

    private fun bindingLocalValute() {
        viewModelLocal.readLastDateData.observe(viewLifecycleOwner) { currency ->
            localValute = currency
            //todo оставил, если вдруг нужно сразу подгружать данные с БД
            //getData()
        }
    }

    private fun initClick() {
        binding.startButton.setOnClickListener {
            getData()
        }
    }

    private fun getData() {
        showProgressBar(true)
        if (viewModelMainFragment.checkNetwork(requireContext())) viewModelRemote.getValute()
        else {
            showToastShort(resources.getString(R.string.not_connection_network))
            showToastShort(resources.getString(R.string.show_data_BD))
            if (localValute != null) {
                localValute?.let {
                    binding.dataText.text =
                        String.format(resources.getString(R.string.data_local_valute_rate), it.timestamp)
                    adapter.setData(viewModelMainFragment.collectList(it.valute))
                    showProgressBar(false)
                }
            } else {
                showToastShort(resources.getString(R.string.not_connection_network_and_clear_BD))
                showProgressBar(false)
            }
        }
    }

    private fun showToastShort(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun showProgressBar(isShow: Boolean){
        if (isShow) binding.progressBar.visibility = View.VISIBLE
        else binding.progressBar.visibility = View.GONE
    }

}
