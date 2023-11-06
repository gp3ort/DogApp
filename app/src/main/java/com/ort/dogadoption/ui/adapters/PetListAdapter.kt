package com.ort.dogadoption
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ort.dogadoption.data.database.DogAppDatabase
import com.ort.dogadoption.data.database.PetsDAO
import com.ort.dogadoption.listener.OnViewItemClickedListener

// Modelo Pets de Pato
//import com.ort.dogadoption.ui.models.Pets

// Modelo Pets de Lucas
import com.ort.dogadoption.models.Pets

class PetListAdapter(private var dataSet: ArrayList<Pets>, private val fragmentIdentifier: String,
                     private val onItemClick: OnViewItemClickedListener): RecyclerView.Adapter<PetListAdapter.ViewHolder>() {

    private var db: DogAppDatabase? = null
    private var petsDAO: PetsDAO? = null

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var itemTitle: TextView
        var itemRace: TextView
        var itemSubrace: TextView
        var itemAge: TextView
        var itemGender: TextView
        var itemFavorite: Switch
        init{
            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.item_title)
            itemRace = itemView.findViewById(R.id.item_race)
            itemSubrace = itemView.findViewById(R.id.item_subrace)
            itemAge = itemView.findViewById(R.id.item_age)
            itemGender = itemView.findViewById(R.id.item_gender)
            itemFavorite = itemView.findViewById(R.id.favorite_switch)
        }

        fun getCardLayout (): CardView {
            return itemView.findViewById(R.id.card_view)
        }
    }

    // Entra por primera vez al Recycler
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout, viewGroup, false)
        // invoco la base
        db = DogAppDatabase.getAppDataBase(v.context)
        petsDAO = db?.petDAO()

        if(fragmentIdentifier == "adoption"){
            val favorite = v.findViewById<Switch>(R.id.favorite_switch)

            favorite.visibility = View.INVISIBLE
        }
        return  ViewHolder(v)
    }

    override fun getItemCount(): Int {
        println("Dateset Size:")
        println(dataSet.size)
        return dataSet.size
    }

    // Agrega cada elemento separado del recycler
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTitle.text = dataSet[i].name
        viewHolder.itemRace.text = dataSet[i].race
        viewHolder.itemSubrace.text = dataSet[i].subrace
        viewHolder.itemAge.text = dataSet[i].age
        viewHolder.itemGender.text = dataSet[i].gender
        viewHolder.itemFavorite.isChecked = dataSet[i].favorite!!
        Glide.with(viewHolder.itemView.context)
            .load(dataSet[i].image).into( viewHolder.itemImage)
        viewHolder.itemView.setOnClickListener {
            println(viewHolder.itemTitle.text)
        }

        viewHolder.itemFavorite.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                petsDAO?.updateFavoritePet(dataSet[i].uid!!, true)
            } else {
                petsDAO?.updateFavoritePet(dataSet[i].uid!!, false)
                if(fragmentIdentifier == "fav"){
                    dataSet.removeAt(i)
                    notifyItemRemoved(i)
                    notifyItemRangeChanged(i, dataSet.size)
                }
            }
        }



        viewHolder.getCardLayout().setOnClickListener{
            val pet = dataSet[i]
            onItemClick.onViewItemDetail(pet)
        }
    }

}



