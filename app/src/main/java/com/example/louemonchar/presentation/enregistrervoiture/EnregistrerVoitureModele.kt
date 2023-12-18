package com.example.louemonchar.presentation.enregistrervoiture

import retrofit2.Callback
import java.sql.Date


class EnregistrerVoitureModele : EnregistrerVoitureInterface.Modele {


    data class nouvelleVoiture(
        val code :String,
        val code_propriétaire: String,
        val marque: String,
        val modèle: String,
        val transmission :String,
        val année: Int,
        val prix: Int,
        val etat: String,
        val passagers: String,
        val propriétaire: String,
        val location: String,
        val imgage: String
    )

    override fun enregistrerNouvelleVoiture(voiture: EnregistrerVoitureModele.nouvelleVoiture, callback: Callback<nouvelleVoiture>) {
        val call = Api.retrofitService.enregistrerVoirure(voiture)
        call.enqueue(callback)
    }
}
