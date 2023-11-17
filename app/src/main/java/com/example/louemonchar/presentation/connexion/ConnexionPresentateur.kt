package com.example.louemonchar.presentation.connexion

import android.util.Log

class ConnexionPresentateur(private val vue: ConnexionInterface.Vue) : ConnexionInterface.Presentateur {

    private val modele: ConnexionInterface.Modele = ConnexionModele()

    override fun tenterConnexion() {
        // Notify the view to navigate to InscriptionFragment
        vue.navigationVersInscriptionFragment()
    }




}






















