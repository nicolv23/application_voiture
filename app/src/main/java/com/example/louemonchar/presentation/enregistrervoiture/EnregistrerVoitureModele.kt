package com.example.louemonchar.presentation.enregistrervoiture

import com.example.louemonchar.http.Auto
import retrofit2.Callback
import java.sql.Date


class EnregistrerVoitureModele : EnregistrerVoitureInterface.Modele {

    override fun enregistrerNouvelleVoiture(voiture: Auto, callback: Callback<Auto>) {
        val call = Api.retrofitService.enregistrerVoirure(voiture)
        call.enqueue(callback)
    }



}
