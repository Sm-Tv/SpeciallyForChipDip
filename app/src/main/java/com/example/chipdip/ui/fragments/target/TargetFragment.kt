package com.example.chipdip.ui.fragments.target

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.chipdip.databinding.FragmentTargetBinding
import com.example.chipdip.local.viewModels.LocalCurrencyViewModel
import com.example.chipdip.model.valute.ItemValute
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import java.text.SimpleDateFormat
import java.util.*
import kotlin.reflect.full.memberProperties


class TargetFragment : Fragment() {

    private lateinit var binding: FragmentTargetBinding
    private val args by navArgs<TargetFragmentArgs>()

    private val viewModelLocal: LocalCurrencyViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentTargetBinding.inflate(layoutInflater)
        bindingData()
        return binding.root
    }

    private fun bindingData() {
        binding.targetValuteName.text = args.nameValute
        val targetValuteChar = args.charValute + ": " + args.valueValute + "₽"
        binding.targetValuteChar.text = targetValuteChar
        viewModelLocal.readAllData.observe(viewLifecycleOwner) { list ->
            val canPop: ArrayList<Entry> = arrayListOf()
            list.forEach { currencyEntity ->
                val dateStr = currencyEntity.remoteTime.split("T")
                val itemValute = currencyEntity.valute.getField<ItemValute>("${args.charValute}")
                val date = convertToDate(dateStr[0])
                itemValute?.value?.let {
                    println(date.time)
                    canPop.add(Entry(date.time.toFloat(), it.toFloat()))
                }
            }
            viewChart(canPop)
        }
    }

    private fun viewChart(canPop: List<Entry>) {
        val dataset = LineDataSet(canPop, "График изменения валюты от времени (value/date)")
        val data = LineData(dataset)

        binding.chart.data = data
        binding.chart.xAxis.valueFormatter = XAxisTimeFormatter()
        binding.chart.invalidate()
    }

    private fun convertToDate(str: String): Date {
        val format = SimpleDateFormat()
        format.applyPattern("yyyy-MM-dd")
        return format.parse(str)
    }

    @Throws(IllegalAccessException::class, ClassCastException::class)
    inline fun <reified T> Any.getField(fieldName: String): T? {
        this::class.memberProperties.forEach { kCallable ->
            if (fieldName == kCallable.name) {
                return kCallable.getter.call(this) as T?
            }
        }
        return null
    }
}
