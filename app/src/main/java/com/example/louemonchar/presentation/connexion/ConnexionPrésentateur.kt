package com.example.louemonchar.presentation.connexion

import android.util.Log

class ConnexionPrésentateur(private val vue: ConnexionInterface.Vue) : ConnexionInterface.Présentateur {

    private val modele: ConnexionInterface.Modèle = ConnexionModèle()

    override fun tenterConnexion() {
        // Notify the view to navigate to InscriptionFragment
        vue.navigationVersInscriptionFragment()
    }




}






















