package com.example.louemonchar.presentation.inscription;

import com.example.louemonchar.presentation.connexion.ConnexionInterface
import com.example.louemonchar.presentation.connexion.ConnexionModèle


class InscriptionPrésentateur(private val vue: InscriptionInterface.Vue) :  InscriptionInterface.Présentateur {

    private val modèle: InscriptionInterface.Modèle = InscriptionModele()

    override fun tenterInscription() {
        // Notify the view to navigate to InscriptionFragment
        vue.navigationVersConnexionFragment()
    }
}
