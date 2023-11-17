package com.example.louemonchar.presentation.inscription

import android.view.View

interface InscriptionInterface {

    interface Vue {
        fun navigationVersConnexionFragment()
    }

    interface Presentateur {
        fun tenterInscription()
    }

    interface Modele {
        fun enregistrerUtilisateur()
    }
}