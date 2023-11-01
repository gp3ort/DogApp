package com.ort.dogadoption.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.fragment.app.activityViewModels
import com.ort.dogadoption.R
import com.ort.dogadoption.ui.viewmodels.SharedInfoViewModel
import dagger.hilt.android.AndroidEntryPoint

class ConfigurationFragment : Fragment() {

    private val sharedInfoViewModel: SharedInfoViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_configuration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toggleNightMode = view.findViewById(R.id.darkModeSwitchId) as Switch

        toggleNightMode.setOnCheckedChangeListener { _, isChecked ->
            sharedInfoViewModel.setDarkMode(isChecked)
        }
    }

}