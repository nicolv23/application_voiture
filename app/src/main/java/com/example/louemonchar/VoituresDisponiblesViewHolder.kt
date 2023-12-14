package com.example.louemonchar

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.louemonchar.databinding.ItemVoituresDisponiblesBinding
import com.example.louemonchar.modèle.VoitureUiModèle
import java.text.DateFormat



/*
class VoituresDisponiblesViewHolder (private val binding: ItemVoituresDisponiblesBinding) : RecyclerView.ViewHolder(binding.root) {

    //temp

        //fin

    fun bind(item: VoitureUiModèle) {
        binding.imgVoituresDisponible.setImageDrawable(ContextCompat.getDrawable(binding.root.context, item.imageRes))
        binding.modLeVoituresDisponible.text = item.modèle
        binding.annEVoituresDisponible.text = DateFormat.getDateInstance(DateFormat.MEDIUM).format(item.année)
        binding.dateDeLocationDisponible.text = DateFormat.getDateInstance(DateFormat.MEDIUM).format(item.location)
        binding.passagersVoituresDisponible.text = item.passagers
        binding.propriTaireVoituresDisponible.text = item.propriétaire


        //binding.dateDeLocation.text = itemView.findViewById(R.id.date_de_location)


    }
}

 */

class VoituresDisponiblesViewHolder(
    private val binding: ItemVoituresDisponiblesBinding,
    private val clickListener: VoitureAdapter.OnItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: VoitureUiModèle, clickListener: VoitureAdapter.OnItemClickListener) {
        binding.imgVoituresDisponible.setImageDrawable(
            ContextCompat.getDrawable(binding.root.context, item.imageRes)
        )
        binding.modLeVoituresDisponible.text = item.modèle
        binding.annEVoituresDisponible.text = DateFormat.getDateInstance(DateFormat.MEDIUM).format(item.année)
        binding.dateDeLocationDisponible.text = DateFormat.getDateInstance(DateFormat.MEDIUM).format(item.location)
        binding.passagersVoituresDisponible.text = item.passagers
        binding.propriTaireVoituresDisponible.text = item.propriétaire

        // Ajoutez un listener sur l'itemView pour gérer les clics
        itemView.setOnClickListener {
            this.clickListener.onItemClick(item)
        }
    }
}

