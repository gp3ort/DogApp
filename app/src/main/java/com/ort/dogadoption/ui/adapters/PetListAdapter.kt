package com.ort.dogadoption
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ort.dogadoption.ui.models.Pets


class PetListAdapter(private var dataSet: ArrayList<Pets>): RecyclerView.Adapter<PetListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var itemTitle: TextView
        var itemRace: TextView
        var itemSubrace: TextView
        var itemAge: TextView
        var itemGender: TextView
        init{
            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.item_title)
            itemRace = itemView.findViewById(R.id.item_race)
            itemSubrace = itemView.findViewById(R.id.item_subrace)
            itemAge = itemView.findViewById(R.id.item_age)
            itemGender = itemView.findViewById(R.id.item_gender)
        }
    }

    // Entra por primera vez al Recycler
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout, viewGroup, false)
        return  ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    // Agrega cada elemento separado del recycler
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTitle.text = dataSet[i].names
        viewHolder.itemRace.text = dataSet[i].race
        viewHolder.itemSubrace.text = dataSet[i].subrace
        viewHolder.itemAge.text = dataSet[i].age.toString()
        viewHolder.itemGender.text = dataSet[i].gender
        viewHolder.itemImage.setImageResource(dataSet[i].image)

        viewHolder.itemView.setOnClickListener {
            println(viewHolder.itemTitle.text)
        }
        }
    }



