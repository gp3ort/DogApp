package com.ort.dogadoption.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ort.dogadoption.PetListAdapter
import com.ort.dogadoption.R
import com.ort.dogadoption.data.database.DogAppDatabase
import com.ort.dogadoption.data.database.PetsDAO
import com.ort.dogadoption.listener.OnViewItemClickedListener
import com.ort.dogadoption.models.Pets


class AdoptionFragment : Fragment(), OnViewItemClickedListener {
    private var db: DogAppDatabase? = null
    private var petsDAO: PetsDAO? = null


    lateinit var v: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_adoption, container, false)

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        db = DogAppDatabase.getAppDataBase(v.context)
        petsDAO = db?.petDAO()

        val mutableList = petsDAO!!.loadAllAdoptedPets()
        val mascotas: ArrayList<Pets> = ArrayList(mutableList)
        val searchList = mascotas

        val petsRecyclerView: RecyclerView = view.findViewById<RecyclerView>(R.id.petsRecyclerView)
        val petsAdapter = PetListAdapter(searchList, "adoption", this)

        petsRecyclerView.layoutManager = LinearLayoutManager(context)
        petsRecyclerView.adapter = petsAdapter

    }

    override fun onViewItemDetail(pet: Pets) {
        val action = AdoptionFragmentDirections.actionAdoptionFragmentToDogDetailFragment(pet)
        this.findNavController().navigate(action)
    }


}