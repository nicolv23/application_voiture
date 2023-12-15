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
        montrerBarreChargement()

        val filtreVoiture = voitureList.filter { it.modèle.contains(query, true) }
        view.afficherVoitures(filtreVoiture)

        cacherBarreChargement()
    }

    override fun setDateLocation(date: java.util.Date) {
        dateLocation = date
    }

    override fun searchByDateRange() {
        val voituresFiltrées = voitureList.filter { it.location == dateLocation }
        view.afficherVoitures(voituresFiltrées)
    }

    private fun montrerBarreChargement() {
        view.montrerBarreChargement()
    }

    private fun cacherBarreChargement() {
        view.cacherBarreChargement()
    }
}