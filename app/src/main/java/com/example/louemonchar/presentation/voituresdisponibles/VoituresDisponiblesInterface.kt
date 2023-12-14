package com.example.louemonchar.presentation.voituresdisponibles

import com.example.louemonchar.modèle.VoitureUiModèle

interface VoituresDisponiblesInterface {

    interface View {
        fun afficherVoitures(voitures: List<VoitureUiModèle>)
        fun afficherErreur(message: String)
    }

    interface Presenter {
        fun chargerVoitures()
        fun rechercherParModèle(query: String)
        fun setDateLocation(date: java.util.Date)
        fun searchByDateRange()
        abstract fun chargerVoituresParModèle(nomModèle: String?)

        //temp

    }
}