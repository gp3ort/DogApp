package com.ort.dogadoption.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.ort.dogadoption.R

class GetStartedFragment : Fragment() {

    lateinit var v: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_get_started, container, false)

        return v;
    }

    override fun onStart() {
        super.onStart()
        val getStartedButton = v.findViewById<Button>(R.id.buttonGetStartedId)
        getStartedButton.setOnClickListener {
            val action = GetStartedFragmentDirections.actionGetStartedFragmentToLoginFragment()
            v.findNavController().navigate(action)
        }
    }

}