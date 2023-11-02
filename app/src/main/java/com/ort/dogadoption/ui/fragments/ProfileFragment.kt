package com.ort.dogadoption.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.ort.dogadoption.R
import com.ort.dogadoption.ui.viewmodels.SharedInfoViewModel


class ProfileFragment : Fragment() {

    private val sharedInfoViewModel: SharedInfoViewModel by activityViewModels()
    lateinit var v: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       v = inflater.inflate(R.layout.fragment_profile, container, false)

        return v
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val profilePhoto = view.findViewById(R.id.profilePhotoId) as ImageView
        val profileName = view.findViewById(R.id.profileNameId) as TextView
        val button = view.findViewById<Button>(R.id.buttonChangePhotoId)

        profileName.text = sharedInfoViewModel.userName.value

        Glide.with(this)
            .load(sharedInfoViewModel.userNamePhoto.value)
            .circleCrop().into(profilePhoto)

        button.setOnClickListener {
            val showPopUp = PhotoFormFragment()
            showPopUp.show((activity as AppCompatActivity).supportFragmentManager, "popUp")
        }

        activity?.let {
            sharedInfoViewModel.userNamePhoto.observe(it, Observer { userNamePhoto ->
                Glide.with(this)
                    .load(userNamePhoto)
                    .circleCrop().into(profilePhoto)
            })
        }
    }
}