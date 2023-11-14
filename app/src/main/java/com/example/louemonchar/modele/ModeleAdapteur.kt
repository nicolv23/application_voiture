package com.example.louemonchar.modele

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.louemonchar.R

class ModeleAdapteur(private val modeleEnregistres: List<String>) : RecyclerView.Adapter<ModeleAdapteur.ModeleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModeleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.modele_enregistements, parent, false)
        return ModeleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ModeleViewHolder, position: Int) {
        val modele = modeleEnregistres[position]
        holder.bind(modele)
    }

    override fun getItemCount(): Int {
        return modeleEnregistres.size
    }

    inner class ModeleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val modeleTextView: TextView = itemView.findViewById(R.id.modeleTextView)

        fun bind(modele: String) {
            modeleTextView.text = modele
        }
    }
}
