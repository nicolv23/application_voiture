package com.example.louemonchar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.louemonchar.http.Auto

class VoitureFavorisAdapter(private var voitureList: List<Auto>) : RecyclerView.Adapter<VoitureFavorisAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val code : TextView = itemView.findViewById(R.id.code_details_voiture)
        val codeProprio : TextView = itemView.findViewById(R.id.details_nom_propriétaire)
        val marqueText: TextView = itemView.findViewById(R.id.marque_modèle_details_voiture)
        val transmission : TextView = itemView.findViewById(R.id.transmission_details_voiture)
        val modele : TextView = itemView.findViewById(R.id.modèle_details_voiture)
        val année : TextView = itemView.findViewById(R.id.annee_details_voiture)
        val location : TextView = itemView.findViewById(R.id.details_date_location)
        val etat : TextView = itemView.findViewById(R.id.details_état)
        val img : TextView = itemView.findViewById(R.id.img_details_voiture)
        val prix : TextView = itemView.findViewById(R.id.details_prix)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_voitures_disponibles, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentVoiture = voitureList[position]
        holder.code.text = currentVoiture.code
        holder.codeProprio.text = currentVoiture.code_propriétaire
        holder.marqueText.text = currentVoiture.marque
        holder.transmission.text = currentVoiture.transmission
        holder.modele.text = currentVoiture.modèle
        holder.année.text = currentVoiture.année.toString()
        holder.etat.text = currentVoiture.etat
        holder.prix.text = currentVoiture.prix.toString()
        holder.img.text = currentVoiture.image
        holder.location.text = currentVoiture.location
    }

    override fun getItemCount(): Int {
        return voitureList.size
    }


    fun miseAjourData(listFavoris: List<Auto>) {
        voitureList = listFavoris
        notifyDataSetChanged()
    }
}