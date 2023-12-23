package com.example.louemonchar.presentation.voituresdisponibles

import android.text.Editable
import com.example.louemonchar.http.Auto
import com.example.louemonchar.modèle.VoitureUiModèle

interface VoituresDisponiblesInterface {

    interface View {
        fun afficherVoitures(voitures: MutableList<Auto>)
        fun afficherErreur(message: String)
        fun montrerBarreChargement()
        fun cacherBarreChargement()
        fun getListe(): MutableList<Auto>


    }

    interface Presenter {
        fun chargerVoitures()
        fun rechercherParModèle(query: String)
        fun setDateLocation(date: java.util.Date)
        fun searchByDateRange()
        fun chargerVoituresParModèle(nomModèle: String?)
        fun rechercherParMarque(query:String)
        fun rechercherParTransmission(query:String)
        //temp

    }
}