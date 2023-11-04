package com.ort.dogadoption.ui.fragments

import com.ort.dogadoption.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import com.ort.dogadoption.ui.viewmodels.BreedViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PubliFragment : Fragment() {

    lateinit var v: View

    private val GENDER = arrayOf(
        "MACHO", "HEMBRA"
    )
    private val breedViewModel: BreedViewModel  by activityViewModels()

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

        val genders = listOf("MACHO", "HEMBRA")
        val autoComplete : AutoCompleteTextView = v.findViewById(R.id.dogGenderId)
        val adapter = ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1, genders)
        autoComplete.setAdapter(adapter)



        val mapBreed = breedViewModel.breeds


        val data = mapBreed.value

// Inicializa una lista vacía para almacenar los datos convertidos
        val resultList = mutableListOf<Pair<String, String>>()

// Verifica si los datos no son nulos
        data?.forEach { (raza, subrazas) ->
            if (subrazas.isNotEmpty()) {
                // Si hay subrazas, agrégales a la lista
                subrazas.forEach { subraza ->
                    resultList.add(raza to subraza)
                }
            } else {
                // Si no hay subrazas, agrega solo la raza
                resultList.add(raza to "")
            }
        }

        val autoCompleteBreed : AutoCompleteTextView = v.findViewById(R.id.dogBreedId)
        val adapterBreed = ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1, resultList)
        autoCompleteBreed.setAdapter(adapterBreed)

        autoComplete.onItemClickListener = AdapterView.OnItemClickListener{
            adapterView, view, i, l ->
            val itemSelected = adapterView.getItemAtPosition(i).toString()
            Toast.makeText(requireActivity(), "Selected: $itemSelected", Toast.LENGTH_SHORT).show()
        }
    }



}