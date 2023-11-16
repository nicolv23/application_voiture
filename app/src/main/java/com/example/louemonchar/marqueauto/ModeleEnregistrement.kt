package com.example.louemonchar.marqueauto

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.louemonchar.R

class ModeleEnregistrement(
    private val context: Context,
    private val modeles: MutableList<String>,
    private val clickListener: ModeleClickListener
) : RecyclerView.Adapter<ModeleEnregistrement.ViewHolder>() {

    interface ModeleClickListener {
        fun onModeleClick(modele: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.modele_enregistrements, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val modele = modeles[position]
        holder.bind(modele)
        holder.itemView.setOnClickListener { clickListener.onModeleClick(modele) }
    }

    override fun getItemCount(): Int {
        return modeles.size
    }

    fun updateModelesEnregistres(nouveauxModelesEnregistres: List<String>) {
        modeles.clear()
        modeles.addAll(nouveauxModelesEnregistres)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val modeleTextView: TextView = itemView.findViewById(R.id.modeleTextView)

        fun bind(modele: String) {
            modeleTextView.text = modele
        }
    }
}
