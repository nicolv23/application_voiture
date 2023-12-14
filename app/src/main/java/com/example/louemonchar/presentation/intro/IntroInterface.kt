package com.example.louemonchar.presentation.intro

interface IntroInterface {

    interface Vue {
        fun afficherEcranPrincipal()
    }

    interface Presentateur {
        fun commencerAnimation()
    }

    interface Modele {
        // Si nécessaire, vous pouvez ajouter des méthodes du modèle ici
    }
}
