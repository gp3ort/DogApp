package com.ort.dogadoption.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ort.dogadoption.PetListAdapter
import com.ort.dogadoption.R
import com.ort.dogadoption.data.database.DogAppDatabase
import com.ort.dogadoption.data.database.PetsDAO

// Modelo Pets de Pato
//import com.ort.dogadoption.ui.models.Pets

// Modelo Pets de Lucas
import com.ort.dogadoption.models.Pets
import java.util.Locale

class HomeFragment : Fragment() {

    lateinit var v: View
    lateinit var imageTest: ImageView

    private var db: DogAppDatabase? = null
    private var petsDAO: PetsDAO? = null

    private lateinit var searchView: SearchView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_home, container, false)

        return v;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // invoco la base
        db = DogAppDatabase.getAppDataBase(v.context)
        petsDAO = db?.petDAO()

        val mutableList = petsDAO!!.loadAllPets()
        val mascotas: ArrayList<Pets> = ArrayList(mutableList)
        val searchList = mascotas

        val petsRecyclerView: RecyclerView = view.findViewById<RecyclerView>(R.id.petsRecyclerView)
        val petsAdapter = PetListAdapter(searchList)

        petsRecyclerView.layoutManager = LinearLayoutManager(context)
        petsRecyclerView.adapter = petsAdapter


    }
    private fun displayToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }
}