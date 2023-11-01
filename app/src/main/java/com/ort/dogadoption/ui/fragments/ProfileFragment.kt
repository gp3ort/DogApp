package com.ort.dogadoption.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.ort.dogadoption.R
import com.ort.dogadoption.ui.viewmodels.SharedInfoViewModel


class ProfileFragment : Fragment() {

    private val sharedInfoViewModel: SharedInfoViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       return inflater.inflate(R.layout.fragment_profile, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val profilePhoto = view.findViewById(R.id.profilePhotoId) as ImageView

        val profileName = view.findViewById(R.id.profileNameId) as TextView

        profileName.text = sharedInfoViewModel.userName.value

        Glide.with(this)
            .load("https://freesvg.org/img/Male-Avatar.png")
            .circleCrop().into(profilePhoto)

    }
}