package com.example.louemonchar.presentation.recuperation

interface RecuperationInterface {
    interface Vue {
        fun afficherErreur(message: String)
        fun lancerNavigation(depart: String, destination: String)
        fun connexion()
    }

    interface Presentateur {
        fun onClickLancerGPS(depart: String, destination: String)
        fun allezConnexion()
    }

    interface Modele {
        // Vous pouvez ajouter des méthodes du modèle ici si nécessaire
    }
}
