package com.example.louemonchar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.louemonchar.databinding.ItemVoituresDisponiblesBinding
import com.example.louemonchar.http.Auto
import com.example.louemonchar.modèle.VoitureUiModèle




class VoitureAdapter(private val clickListener: OnItemClickListener) : RecyclerView.Adapter<VoituresDisponiblesViewHolder>() {

    private val mItemList = mutableListOf<Auto>()

    interface OnItemClickListener {
        fun onItemClick(voiture: Auto)
    }

    fun setItems(itemList: MutableList<Auto>) {
        mItemList.clear()
        mItemList.addAll(itemList)
        notifyDataSetChanged()
    }

    fun addItem(item: Auto) {
        mItemList.add(item)
        notifyItemInserted(mItemList.size - 1)
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
