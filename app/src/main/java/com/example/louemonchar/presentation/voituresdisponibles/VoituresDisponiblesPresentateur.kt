package com.example.louemonchar.presentation.voituresdisponibles

import com.example.louemonchar.VoitureMock
import com.example.louemonchar.modèle.VoitureUiModèle


class VoituresDisponiblesPresentateur(private val view: VoituresDisponiblesInterface.View) : VoituresDisponiblesInterface.Presenter {
    private val voitureList: List<VoitureUiModèle> = VoitureMock.getListeVoiture()
    private var dateLocation: java.util.Date? = null

    override fun chargerVoitures() {
        view.afficherVoitures(voitureList)
    }

    override fun rechercherParModèle(query: String) {
        val filtreVoiture = voitureList.filter { it.modèle.contains(query, true) }
        view.afficherVoitures(filtreVoiture)
    }

    override fun setDateLocation(date: java.util.Date) {
        dateLocation = date
    }

    override fun searchByDateRange() {
        val voituresFiltrées = voitureList.filter { it.location == dateLocation }
        view.afficherVoitures(voituresFiltrées)
    }


    //code temporaire
    override fun chargerVoituresParModèle(nomModèle: String?) {
        nomModèle?.let {
            val voituresFiltrées = voitureList.filter { it.modèle.equals(nomModèle, ignoreCase = true) }
            if (voituresFiltrées.isNotEmpty()) {
                view.afficherVoitures(voituresFiltrées)
            } else {
                view.afficherErreur("Modèle $nomModèle non disponible. Voici les modèles disponibles.")
            }
        } ?: view.afficherErreur("Modèle non disponible")
    }
}