package com.example.chipdip.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chipdip.databinding.FragmentTargetBinding

class TargetFragment : Fragment() {

    private lateinit var binding: FragmentTargetBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentTargetBinding.inflate(layoutInflater)
        return binding.root
    }

}
