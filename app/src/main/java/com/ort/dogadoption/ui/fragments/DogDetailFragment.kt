package com.ort.dogadoption.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.ort.dogadoption.R
import com.ort.dogadoption.data.database.DogAppDatabase
import com.ort.dogadoption.data.database.PetsDAO


class DogDetailFragment : Fragment() {

    lateinit var v: View
    private var db: DogAppDatabase? = null
    private var petsDAO: PetsDAO? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_dog_detail, container, false)
        db = DogAppDatabase.getAppDataBase(v.context)
        petsDAO = db?.petDAO()
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pet = arguments?.let { DogDetailFragmentArgs.fromBundle(it).pet }!!
        val nameDetail = view.findViewById<TextView>(R.id.detailNameId)
        val ageDetail = view.findViewById<TextView>(R.id.detailAgeId)
        val genderDetail = view.findViewById<TextView>(R.id.detailGenderId)
        val weightDetail = view.findViewById<TextView>(R.id.detailPesoId)
        val imageDetail = view.findViewById<ImageView>(R.id.detailImageId)
        val backButton = view.findViewById<Button>(R.id.detailBackButtonId)
        val phoneButton = view.findViewById<Button>(R.id.detailPhoneButtonId)
        val phoneDetail = view.findViewById<TextView>(R.id.detailPhoneId)

        val adoptionButton = view.findViewById<TextView>(R.id.detailButtonId)
        val adoptedText = view.findViewById<TextView>(R.id.detailAdoptedText)


        if(pet.adopted!!){
            adoptionButton.visibility = View.INVISIBLE
        }else {
            adoptedText.visibility = View.INVISIBLE
        }

        backButton.setOnClickListener{
            it.findNavController().navigateUp()
        }

        phoneButton.setOnClickListener{
            val intent = Intent(Intent.ACTION_DIAL, android.net.Uri.parse("tel:" + phoneDetail.text.toString()))
            startActivity(intent)
        }

        adoptionButton.setOnClickListener{
            petsDAO?.updateAdoption(pet.uid!!, true)
            displayToast("Tu perrito ha sido adoptado !")
            it.findNavController().navigateUp()
        }

        nameDetail.text = pet.name
        ageDetail.text = pet.age
        genderDetail.text = pet.gender
        weightDetail.text = pet.weigth.toString()
        phoneDetail.text = pet.phone.toString()
        Glide.with(this)
            .load(pet.image).into(imageDetail)

    }

    private fun displayToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

}