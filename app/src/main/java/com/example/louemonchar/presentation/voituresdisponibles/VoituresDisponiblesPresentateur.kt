package com.example.louemonchar.presentation.voituresdisponibles

import com.example.louemonchar.VoitureMock
import com.example.louemonchar.http.Auto
import com.example.louemonchar.modèle.VoitureUiModèle

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class VoituresDisponiblesPresentateur(private val view: VoituresDisponiblesInterface.View) : VoituresDisponiblesInterface.Presenter {
    private val voitureList: List<Auto> = view.getListe()
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
        val voituresFiltrées = voitureList.filter {Date(it.location.toDateFormat().time) == dateLocation }
        view.afficherVoitures(voituresFiltrées)
    }

    private fun montrerBarreChargement() {
        view.montrerBarreChargement()
    }

    private fun cacherBarreChargement() {
        view.cacherBarreChargement()
    }

    fun String.toDateFormat(): Date {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.parse(this) ?: throw ParseException("Invalid date format", 0)
    }

}