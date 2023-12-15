// AccueilPresentateur.kt

package com.example.louemonchar.presentation.accueil

import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AccueilPresentateur(private val accueilVue: AccueilVue, private val accueilModèle: AccueilModèle) {
    /*
    fun onItemClick(position: Int) {
        accueilVue.montrerChargement()

        GlobalScope.launch(Dispatchers.Main) {
            // Délai simulé de 1,5 seconde avant de naviguer vers un autre fragment
            delay(1500)
            accueilVue.cacherChargement()

            // Afficher un Snackbar pour informer que le chargement est terminé
            Snackbar.make(accueilVue.requireView(), "Chargement terminé", Snackbar.LENGTH_SHORT).show()

            val action = AccueilVueDirections.actionAccueilVueToVoituresDisponiblesVue()
            accueilVue.findNavController().navigate(action)
        }
    }
}

     */

    fun onItemClick(position: Int) {
        val nomVoiture = accueilModèle.getNomVoiture(position)
        val action = AccueilVueDirections.actionAccueilVueToVoituresDisponiblesVue(nomVoiture)
        accueilVue.findNavController().navigate(action)
    }
}
