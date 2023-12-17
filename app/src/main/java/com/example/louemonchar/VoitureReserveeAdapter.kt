package com.example.louemonchar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.louemonchar.http.Auto

class VoitureReserveeAdapter(
    private val voitures: MutableList<VoitureReservee>,
    private val onRetirerClicked: (Int) -> Unit
) : RecyclerView.Adapter<VoitureReserveeAdapter.VoitureViewHolder>() {

    class VoitureViewHolder(view: View, onRetirerClicked: (Int) -> Unit) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.img_voitures_disponible)
        val marqueModèleView: TextView = view.findViewById(R.id.modèle_voitures_disponible)
        val annéeView: TextView = view.findViewById(R.id.année_voitures_disponible)
        val passagersView: TextView = view.findViewById(R.id.passagers_voitures_disponible)
        val propriétaireView: TextView = view.findViewById(R.id.propriétaire_voitures_disponible)
        val dateLocationView: TextView = view.findViewById(R.id.date_de_location_disponible)
        private val btnRetirer: Button = view.findViewById(R.id.retirer)

        init {
            btnRetirer.setOnClickListener {
                onRetirerClicked(adapterPosition)
            }
        }

        fun bind(voiture: VoitureReservee) {

            Glide.with(itemView.context).load(voiture.imageRes).into(imageView)
            marqueModèleView.text = voiture.marqueModèle
            annéeView.text = voiture.année
            passagersView.text = 5.toString()
            propriétaireView.text = voiture.propriétaire
            dateLocationView.text = "Disponible"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VoitureViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_voiture_reservee, parent, false)
        return VoitureViewHolder(view, onRetirerClicked)
    }

    override fun onBindViewHolder(holder: VoitureViewHolder, position: Int) {
        val voiture = voitures[position]
        holder.bind(voiture)
    }

    override fun getItemCount() = voitures.size
}

