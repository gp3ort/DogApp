package com.ort.dogadoption.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ort.dogadoption.PetListAdapter
import com.ort.dogadoption.R
import com.ort.dogadoption.data.database.DogAppDatabase
import com.ort.dogadoption.data.database.PetsDAO
import com.ort.dogadoption.listener.OnViewItemClickedListener
import com.ort.dogadoption.models.Pets
import java.util.Locale

class HomeFragment : Fragment(), OnViewItemClickedListener {

    lateinit var v: View
    lateinit var imageTest: ImageView

    private var db: DogAppDatabase? = null
    private var petsDAO: PetsDAO? = null

    private lateinit var searchView: SearchView
    private lateinit var searchList: ArrayList<Pets>

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
        searchList = ArrayList()

        // invoco la base
        db = DogAppDatabase.getAppDataBase(v.context)
        petsDAO = db?.petDAO()

        val mutableList = petsDAO!!.loadAllPets()

        if(mutableList.isEmpty()){
            var textEmpty = v.findViewById<TextView>(R.id.emptyMessageId)
            textEmpty.visibility = View.VISIBLE
        }else{
            var textEmpty = v.findViewById<TextView>(R.id.emptyMessageId)
            textEmpty.visibility = View.INVISIBLE
        }

        val mascotas: ArrayList<Pets> = ArrayList(mutableList)
        searchList.addAll(mascotas)

        val petsRecyclerView: RecyclerView = view.findViewById<RecyclerView>(R.id.petsRecyclerView)

        val petsAdapter = PetListAdapter(searchList, "home", this)

        petsRecyclerView.layoutManager = LinearLayoutManager(context)
        petsRecyclerView.adapter = petsAdapter

        searchView = view.findViewById(R.id.search)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                searchView.clearFocus()
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                searchList.clear()
                val searchText = newText!!.toLowerCase(Locale.getDefault())
                if (searchText.isNotEmpty()){
                    mascotas.forEach{
                        if (it.name.toLowerCase(Locale.getDefault()).contains(searchText)){
                            searchList.add(it)
                        }
                    }
                    petsRecyclerView.adapter!!.notifyDataSetChanged()
                }else{
                    searchList.clear()
                    searchList.addAll(mascotas)
                    petsRecyclerView.adapter!!.notifyDataSetChanged()
                }
                return false
            }
        })
    }

    override fun onViewItemDetail(pet: Pets) {
        val action = HomeFragmentDirections.actionHomeFragmentToDogDetailFragment(pet)
        this.findNavController().navigate(action)
    }
}