package com.example.louemonchar.presentation.inscription;

import com.example.louemonchar.presentation.connexion.ConnexionInterface
import com.example.louemonchar.presentation.connexion.ConnexionModele


class InscriptionPresentateur(private val vue: InscriptionInterface.Vue) :  InscriptionInterface.Presentateur {

    private val modele: InscriptionInterface.Modele = InscriptionModele()

    override fun tenterInscription() {
        // Notify the view to navigate to InscriptionFragment
        vue.navigationVersConnexionFragment()
    }
}
