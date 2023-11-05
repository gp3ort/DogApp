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
    private lateinit var profilePhoto: ImageView
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

        profilePhoto = view.findViewById(R.id.profilePhotoId) as ImageView
        val profileName = view.findViewById(R.id.profileNameId) as TextView
        val button = view.findViewById<Button>(R.id.buttonChangePhotoId)


        profileName.text = sharedInfoViewModel.userName.value
        setPhoto(sharedInfoViewModel.userNamePhoto.value!!)

        button.setOnClickListener {
            val showPopUp = PhotoFormFragment()
            showPopUp.show((activity as AppCompatActivity).supportFragmentManager, "popUp")
        }

        activity?.let {
            sharedInfoViewModel.userNamePhoto.observe(it, Observer { userNamePhoto ->
                setPhoto(userNamePhoto)
            })
        }
    }

    private fun setPhoto(url: String){
        Glide.with(this)
            .load(url)
            .circleCrop().into(profilePhoto)
    }
}