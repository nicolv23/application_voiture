package com.example.louemonchar.presentation.inscription

import android.view.View

interface InscriptionInterface {

    interface Vue {
        fun navigationVersConnexionFragment()
    }

    interface Présentateur {
        fun tenterInscription()
    }

    interface Modèle {
        fun enregistrerUtilisateur()
    }
}