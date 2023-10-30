package com.example.louemonchar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class MarquesAuto : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_marques_auto, container, false)

        // Récupérer le GridLayout
        val gridLayout: GridLayout = view.findViewById(R.id.gridLayout)

        // Itérer sur les enfants du GridLayout
        for (i in 0 until gridLayout.childCount) {
            val child: View = gridLayout.getChildAt(i)
            if (child is ImageView) {
                // Ajouter le setOnClickListener pour chaque ImageView
                child.setOnClickListener {
                    val marque = when (child.id) {
                        R.id.toyota -> "Toyota"
                        R.id.honda -> "Honda"
                        R.id.bmw -> "BMW"
                        R.id.porsche -> "Porsche"
                        R.id.mazda -> "Mazda"
                        R.id.hyundai -> "Hyundai"
                        R.id.tesla -> "Tesla"
                        R.id.lexus -> "Lexus"
                        R.id.mercedes -> "Mercedes"
                        R.id.jeep -> "Jeep"
                        R.id.volvo -> "Volvo"
                        R.id.nissan -> "Nissan"
                        // Ajoutez d'autres cas pour les autres marques
                        else -> "Cette marque n'est pas dans la liste"
                    }

                    val action = MarquesAutoDirections.actionMarquesAutoToListeVoitures(marque)
                    findNavController().navigate(action)
                }
            }
        }
        return view
    }

    private fun setToolbarTitle(title: String) {
        // Changer le titre de la barre d'outils ou de toute autre vue appropriée
        (requireActivity() as AppCompatActivity).supportActionBar?.title = title
    }
}


