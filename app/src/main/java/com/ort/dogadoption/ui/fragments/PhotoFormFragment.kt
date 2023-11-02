package com.ort.dogadoption.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.ort.dogadoption.R
import com.ort.dogadoption.ui.viewmodels.SharedInfoViewModel


class PhotoFormFragment : DialogFragment() {

    lateinit var v: View
    private val sharedInfoViewModel: SharedInfoViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_photo_form, container, false)

        return v
    }


    override fun onStart() {
        super.onStart()

        val acceptButton = v.findViewById<Button>(R.id.photoButtonAcceptId)
        val cancelButton = v.findViewById<Button>(R.id.photoButtonCancelId)

        acceptButton.setOnClickListener {
            val textUrl = v.findViewById<TextView>(R.id.urlPhotoId)
            sharedInfoViewModel.setUsernamePhoto(textUrl.text.toString())
            dismiss()
        }

        cancelButton.setOnClickListener{
            dismiss()
        }
    }

}