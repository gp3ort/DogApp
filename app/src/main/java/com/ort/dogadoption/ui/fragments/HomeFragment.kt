package com.ort.dogadoption.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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

class HomeFragment : Fragment() {

    lateinit var v: View
    lateinit var imageTest: ImageView

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
        v = inflater.inflate(R.layout.fragment_home, container, false)

        return v;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Agregado de Mascotas
        //mascotas.add(Pets("Cartucho", "Barbincho", "Peludo", 3, "Macho", R.drawable.perro1))
        //mascotas.add(Pets("Tutuca", "Labrador", "Obeso", 3, "Hembra", R.drawable.perro2))
        //mascotas.add(Pets("Fatiga", "Vago", "Dormilon", 3, "Macho", R.drawable.perro3))
        //mascotas.add(Pets("Sultan", "Golden", "De Oro", 3, "Macho", R.drawable.perro4))
        //mascotas.add(Pets("Max", "Coquer", "Spaniel", 3, "Hembra", R.drawable.perro5))

        // invoco la base
        db = DogAppDatabase.getAppDataBase(v.context)
        petsDAO = db?.petDAO()

        val mascotas = ArrayList<Pets>()
        val mutableList = petsDAO!!.loadAllPets()

        if (mutableList.isNullOrEmpty()){
            println("La lista esta vacia!!")
        }else{
            println("La lista tiene datos!!")
            println(mutableList.size)

            val mascotas: ArrayList<Pets> = ArrayList(mutableList)
            println("Mascotas:")
            println(mascotas.size)
        }

        val petsRecyclerView: RecyclerView = view.findViewById<RecyclerView>(R.id.petsRecyclerView)
        val petsAdapter = PetListAdapter(mascotas)

        petsRecyclerView.layoutManager = LinearLayoutManager(context)
        petsRecyclerView.adapter = petsAdapter

        //card.setOnClickListener{
        //    displayToast("Hiciste Click!")
        //}

    }
    private fun displayToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }
}