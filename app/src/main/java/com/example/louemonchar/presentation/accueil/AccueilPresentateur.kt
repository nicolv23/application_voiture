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


    fun onItemClick(position: Int) {
        val nomVoiture = accueilModèle.getNomVoiture(position)
        val action = AccueilVueDirections.actionAccueilVueToVoituresDisponiblesVue(nomVoiture)
        accueilVue.findNavController().navigate(action)
    }
}
