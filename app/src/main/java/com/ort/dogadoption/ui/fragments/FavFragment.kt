package com.ort.dogadoption.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ort.dogadoption.PetListAdapter
import com.ort.dogadoption.R
import com.ort.dogadoption.data.database.DogAppDatabase
import com.ort.dogadoption.data.database.PetsDAO
import com.ort.dogadoption.listener.OnViewItemClickedListener
import com.ort.dogadoption.models.Pets


class FavFragment : Fragment(), OnViewItemClickedListener {

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
        v = inflater.inflate(R.layout.fragment_fav, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        db = DogAppDatabase.getAppDataBase(v.context)
        petsDAO = db?.petDAO()

        val mutableList = petsDAO!!.loadAllFavoritesPets()
        val mascotas: ArrayList<Pets> = ArrayList(mutableList)
        val searchList = mascotas

        val petsRecyclerView: RecyclerView = view.findViewById<RecyclerView>(R.id.petsRecyclerView)
        val petsAdapter = PetListAdapter(searchList, "fav", this)

        petsRecyclerView.layoutManager = LinearLayoutManager(context)
        petsRecyclerView.adapter = petsAdapter

    }

    override fun onViewItemDetail(pet: Pets) {
        val action = FavFragmentDirections.actionFavFragmentToDogDetailFragment(pet)
        this.findNavController().navigate(action)
    }
}