package com.example.louemonchar.détail

import androidx.navigation.NavController

class DetailPresentateur {

    fun allezVersPaiement(navController: NavController){
        val action =
            EcranDetailDirections.actionÉcranDétailToVuePaiement()
        navController.navigate(action)
    }

    fun allezVersContact(navController: NavController, modeleSelectionne: String) {
        val action =
            EcranDetailDirections.actionÉcranDétailToContact(modeleSelectionne, modeleSelectionne)
        navController.navigate(action)
    }
}