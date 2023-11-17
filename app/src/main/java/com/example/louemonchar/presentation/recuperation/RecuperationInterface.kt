package com.example.louemonchar.presentation.recuperation

interface RecuperationInterface {
    interface Vue {
        fun afficherErreur(message: String)
        fun lancerNavigation(depart: String, destination: String)
    }

    interface Presentateur {
        fun onClickLancerGPS(depart: String, destination: String)
    }

    interface Modele {
        // Vous pouvez ajouter des méthodes du modèle ici si nécessaire
    }
}
