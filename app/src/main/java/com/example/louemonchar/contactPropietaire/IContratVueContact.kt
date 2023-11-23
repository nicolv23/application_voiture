package com.example.louemonchar.contactPropietaire

import com.example.louemonchar.sourceDonnees.ProprietaireModele

interface IContratVueContact {
    interface Vue {
        fun afficherDetailsProprietaire(proprietaire: ProprietaireModele?)
    }

    interface Presentateur {
        fun recupererDetailsProprietaire(marqueVoiture: String, modeleVoiture: String)
    }
}
