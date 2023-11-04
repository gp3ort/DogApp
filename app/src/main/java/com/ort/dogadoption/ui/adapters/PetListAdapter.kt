package com.ort.dogadoption
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PetListAdapter: RecyclerView.Adapter<PetListAdapter.ViewHolder>() {

    val titles = arrayOf("Cartucho",
            "Tutuca",
            "Fatiga",
            "Sultan",
            "Max")

    val races = arrayOf("Barbincho",
        "Labrador",
        "Vago",
        "Golden",
        "Coquer")

    val subraces = arrayOf("Peludo",
        "Obeso",
        "Dormilon",
        "de Oro",
        "Spaniel")

    val ages = arrayOf("3",
        "2",
        "5",
        "16",
        "7")

    val genders = arrayOf("Macho",
        "Hembra",
        "Macho",
        "Macho",
        "Hembra")

    val images = intArrayOf(R.drawable.perro1,
        R.drawable.perro2,
        R.drawable.perro3,
        R.drawable.perro4,
        R.drawable.perro5)

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
        return titles.size
    }

    // Agrega cada elemento separado del recycler
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTitle.text = titles[i]
        viewHolder.itemRace.text = races[i]
        viewHolder.itemSubrace.text = subraces[i]
        viewHolder.itemAge.text = ages[i]
        viewHolder.itemGender.text = genders[i]
        viewHolder.itemImage.setImageResource(images[i])
    }


}