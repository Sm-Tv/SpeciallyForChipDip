package com.example.chipdip.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chipdip.databinding.FragmentTargetBinding
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData

import com.github.mikephil.charting.data.LineDataSet




class TargetFragment : Fragment() {

    private lateinit var binding: FragmentTargetBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentTargetBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        val canPop = listOf(
            Entry(1851F, 2.436F),
            Entry(1861F, 3.23F),
            Entry(1871F, 3.689F),
            Entry(1881F, 4.325F),
            Entry(1891F, 4.833F),
            Entry(1901F, 5.371F),
            Entry(1911F, 7.207F),
            Entry(1921F, 8.788F),
            Entry(1931F, 10.377F),
        )
        viewChart(canPop)
        super.onStart()
    }



    private fun viewChart(canPop: List<Entry>){
        val dataset = LineDataSet(canPop, "График первый")
        val data = LineData(dataset)
        binding.chart.data = data
        binding.chart.invalidate()
    }
}
