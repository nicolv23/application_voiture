package com.example.louemonchar.presentation.voituresdisponibles

import com.example.louemonchar.VoitureMock
import com.example.louemonchar.http.Auto
import com.example.louemonchar.modèle.VoitureUiModèle

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class VoituresDisponiblesPresentateur(private val view: VoituresDisponiblesInterface.View) : VoituresDisponiblesInterface.Presenter {
    private val voitureList: MutableList<Auto> = view.getListe()
    private var dateLocation: java.util.Date? = null

    override fun chargerVoitures() {
        view.afficherVoitures(voitureList)
    }

    override fun rechercherParModèle(query: String) {
        montrerBarreChargement()
        val filtreVoiture = voitureList.filter { it.modèle.contains(query, true) }
        view.afficherVoitures(filtreVoiture.toMutableList())
        cacherBarreChargement()
    }

    override fun rechercherParMarque(query:String){
        montrerBarreChargement()
        val filtreVoitureParMarque = voitureList.filter { it.marque.contains(query,true) }
        view.afficherVoitures(filtreVoitureParMarque.toMutableList())
        cacherBarreChargement()
    }

    override fun rechercherParTransmission(query:String){
        montrerBarreChargement()
        val filtreVoitureParPrix = voitureList.filter { it.transmission.contains(query,true) }
        view.afficherVoitures(filtreVoitureParPrix.toMutableList())
        cacherBarreChargement()
    }

    override fun setDateLocation(date: java.util.Date) {
        dateLocation = date
    }

    override fun searchByDateRange() {
        val voituresFiltrées = voitureList.filter { it.location?.toDateFormat()
            ?.let { it1 -> Date(it1.time) } == dateLocation }
        view.afficherVoitures(voituresFiltrées.toMutableList())


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

    //code temporaire
    override fun chargerVoituresParModèle(nomModèle: String?) {
        nomModèle?.let {
            val voituresFiltrées = voitureList.filter { it.marque.equals(nomModèle, ignoreCase = true) }
            if (voituresFiltrées.isNotEmpty()) {
                view.afficherVoitures(voituresFiltrées.toMutableList())
            } else {
                view.afficherErreur("Marque $nomModèle non disponible. Voici les modèles disponibles.")
            }
        }
    }

}