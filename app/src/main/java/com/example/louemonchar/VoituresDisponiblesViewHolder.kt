package com.example.louemonchar

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.louemonchar.databinding.ItemVoituresDisponiblesBinding
import com.example.louemonchar.http.Auto
import com.example.louemonchar.modèle.VoitureUiModèle
import com.example.louemonchar.presentation.voituresdisponibles.VoituresDisponiblesVue
import java.sql.Date
import java.text.DateFormat
import java.text.SimpleDateFormat




class VoituresDisponiblesViewHolder(
    private val binding: ItemVoituresDisponiblesBinding,
    private val clickListener: VoitureAdapter.OnItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    val format = SimpleDateFormat("yyyy.MM.dd HH:mm")

    fun bind(item: Auto, clickListener: VoitureAdapter.OnItemClickListener) {




        Glide.with(itemView.context).load(item.image).into(binding.imgVoituresDisponible)

        binding.modLeVoituresDisponible.text = item.marque// + " " + item.modèle
        binding.annEVoituresDisponible.text = "Année : " + " " + item.année.toString()
        binding.modelVoiture.text=  "Modèle : "+item.modèle
        binding.prixVoiture.text = "Prix : " +item.prix
        binding.tranmissionVoituresDisponible.text = "Transmission : "+item.transmission
        binding.dateDeLocationDisponible.text = "Date location :" + " " + item.location
        binding.passagersVoituresDisponible.text = "Passagers : "+ 5.toString()
        binding.propriTaireVoituresDisponible.text = "Proprio : " + " " + item.code_propriétaire

        // Ajoutez un listener sur l'itemView pour gérer les clics
        itemView.setOnClickListener {
            this.clickListener.onItemClick(item)
        }
    }
}

