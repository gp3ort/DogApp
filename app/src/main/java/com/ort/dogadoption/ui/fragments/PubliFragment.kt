package com.ort.dogadoption.ui.fragments

import com.ort.dogadoption.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.ort.dogadoption.data.api.DogApiService
import com.ort.dogadoption.data.database.DogAppDatabase
import com.ort.dogadoption.data.database.PetsDAO
import com.ort.dogadoption.models.Pets
import com.ort.dogadoption.ui.viewmodels.BreedViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID
import kotlin.random.Random


class PubliFragment : Fragment() {

    lateinit var v: View
    lateinit var dogApi: DogApiService
    val resultList = mutableListOf<Pair<String, String>>()
    private val breedViewModel: BreedViewModel by activityViewModels()

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
        v = inflater.inflate(R.layout.fragment_publi, container, false)
        return v;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = DogAppDatabase.getAppDataBase(v.context)



        val progressBar = v.findViewById<ProgressBar>(R.id.progressBar)
        val breedSpinner = v.findViewById<Spinner>(R.id.dogBreedId)
        val buttonPublish = v.findViewById<Button>(R.id.dogPublishButtonId)

        progressBar.visibility = View.VISIBLE
        breedSpinner.visibility = View.INVISIBLE
        buttonPublish.visibility = View.INVISIBLE

        if (breedViewModel.breeds.value == null) {
            dogApi = DogApiService()
            getAllBreeds()
        } else {
            // Los datos ya están disponibles, configura el AutoCompleteTextView
            val breeds = breedViewModel.breeds.value
            setupAutoCompleteTextView(breeds)
        }

        val genderSpinner: Spinner = v.findViewById(R.id.dogGenderId)


        val data = listOf("Hembra", "Macho")
        val adapter = ArrayAdapter(requireActivity(), R.layout.spinner_item_layout, data)
        adapter.setDropDownViewResource(R.layout.spinner_item_layout)
        genderSpinner.adapter = adapter


        breedViewModel.breeds.observe(viewLifecycleOwner, Observer { breeds ->
            setupAutoCompleteTextView(breeds)
            progressBar.visibility = View.INVISIBLE
            breedSpinner.visibility = View.VISIBLE
            buttonPublish.visibility = View.VISIBLE
        })

        buttonPublish.setOnClickListener {


            val breed = breedSpinner.selectedItem.toString()
            val gender = genderSpinner.selectedItem.toString()
            val name = v.findViewById<TextView>(R.id.dogNameId)
            val age = v.findViewById<TextView>(R.id.dogAgeId)

            if(validateInputData(breed, gender, name.text.toString(), age.text.toString())){
                petsDAO = db?.petDAO()
                val pet = Pets(name.text.toString(), breed,
                    breed, gender, age.text.toString(), "test")

                petsDAO?.insertPet(pet)
                displayToast("Perro cargado correctamente !!")
                name.setText("")
                age.setText("")
                breedSpinner.setSelection(0)
                genderSpinner.setSelection(0)
            }
        }
    }


    private fun validateInputData(breed: String, gender: String, name: String, age: String): Boolean{
        if (name.isEmpty()) {
            displayToast("Ingrese nombre, por favor!")
            return false
        }

        if (gender.isEmpty()) {
            displayToast("Ingrese el genero, por favor!")
            return false
        }

        if (breed.isEmpty()) {
            displayToast("Ingrese la raza, por favor!")
            return false
        }

        if (age.isEmpty()) {
            displayToast("Ingrese la edad, por favor!")
            return false
        }

        return true
    }


    private fun displayToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    private fun setupAutoCompleteTextView(breeds: Map<String, List<String>>?) {
        if (breeds != null) {
            for (breed in breeds) {
                for (subBreed in breed.value) {
                    resultList.add(Pair(breed.key, subBreed))
                }
            }
        }
        val spinner: Spinner = v.findViewById(R.id.dogBreedId)
        val adapterBreed = ArrayAdapter(requireActivity(), R.layout.spinner_item_layout, resultList)
        adapterBreed.setDropDownViewResource(R.layout.spinner_item_layout)
        spinner.adapter = adapterBreed
    }

    private fun getAllBreeds(){
        CoroutineScope(Dispatchers.IO).launch {
            val response = dogApi.getBreeds()
            if(response.isSuccessful){
                response.body()?.let {
                    response.body()!!.message.let { it1 -> breedViewModel.setBreeds(it1) }}
            }else{
                Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }
}