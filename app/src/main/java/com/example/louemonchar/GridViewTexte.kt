package com.example.louemonchar

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class GridViewTexte(context: Context, resource: Int, private var objects: MutableList<String>) :
    ArrayAdapter<String>(context, resource, objects) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val textView = super.getView(position, convertView, parent) as TextView
        textView.textSize = 24f
        textView.setTextColor(Color.WHITE)
        textView.gravity = Gravity.CENTER
        textView.setPadding(16, 16, 16, 16)
        return textView
    }

    fun updateData(newData: MutableList<String>) {
        objects = newData
        notifyDataSetChanged()
    }
}
