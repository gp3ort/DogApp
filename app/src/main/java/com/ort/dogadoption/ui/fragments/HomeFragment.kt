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
import com.ort.dogadoption.databinding.ActivityLoginBinding
import com.ort.dogadoption.databinding.ActivityMainBinding
import com.ort.dogadoption.ui.models.Pets
import com.ort.dogadoption.ui.views.MainActivityArgs
import com.squareup.picasso.Picasso

class HomeFragment : Fragment() {

    lateinit var v: View
    lateinit var imageTest: ImageView
    val mascotas = ArrayList<Pets>()

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
        mascotas.add(Pets("Cartucho", "Barbincho", "Peludo", 3, "Macho", R.drawable.perro1))
        mascotas.add(Pets("Tutuca", "Labrador", "Obeso", 3, "Hembra", R.drawable.perro2))
        mascotas.add(Pets("Fatiga", "Vago", "Dormilon", 3, "Macho", R.drawable.perro3))
        mascotas.add(Pets("Sultan", "Golden", "De Oro", 3, "Macho", R.drawable.perro4))
        mascotas.add(Pets("Max", "Coquer", "Spaniel", 3, "Hembra", R.drawable.perro5))


        val petsRecyclerView: RecyclerView = view.findViewById<RecyclerView>(R.id.petsRecyclerView)
        val petsAdapter = PetListAdapter(mascotas)
        petsRecyclerView.layoutManager = LinearLayoutManager(context)
        petsRecyclerView.adapter = petsAdapter
        val card = view.findViewById<ImageView>(R.id.item_title)

        //card.setOnClickListener{
        //    displayToast("Hiciste Click!")
        //}

    }
    private fun displayToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }
}