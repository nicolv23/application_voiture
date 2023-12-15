package com.example.louemonchar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.louemonchar.databinding.ItemVoituresDisponiblesBinding
import com.example.louemonchar.modèle.VoitureUiModèle




class VoitureAdapter(private val clickListener: OnItemClickListener) : RecyclerView.Adapter<VoituresDisponiblesViewHolder>() {

    private val mItemList = mutableListOf<VoitureUiModèle>()

    interface OnItemClickListener {
        fun onItemClick(voiture: VoitureUiModèle)
    }

    fun setItems(itemList: List<VoitureUiModèle>) {
        mItemList.clear()
        mItemList.addAll(itemList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VoituresDisponiblesViewHolder {
        val binding = ItemVoituresDisponiblesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VoituresDisponiblesViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: VoituresDisponiblesViewHolder, position: Int) {
        val currentVoiture = mItemList[position]
        holder.bind(currentVoiture, clickListener)
    }


    override fun getItemCount(): Int {
        return mItemList.size
    }
}