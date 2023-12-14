// AccueilPresentateur.kt

package com.example.louemonchar.presentation.accueil

import androidx.navigation.fragment.findNavController

class AccueilPresentateur(private val accueilVue: AccueilVue, private val accueilModèle: AccueilModèle) {
    /*
    fun onItemClick(position: Int) {
        val action = AccueilVueDirections.actionAccueilVueToVoituresDisponiblesVue(

        )
        accueilVue.findNavController().navigate(action)
    }
}

     */

    fun onItemClick(position: Int) {
        val nomVoiture = accueilModèle.getNomVoiture(position)
        val action = AccueilVueDirections.actionAccueilVueToVoituresDisponiblesVue(nomVoiture)
        accueilVue.findNavController().navigate(action)
    }
}
