package com.example.louemonchar.presentation.accueil

import android.view.View
import com.example.louemonchar.R

class AccueilModèle : AccueilInterface {
    private val nomVoiture = arrayOf(
        "BMW", "HONDA", "HYUNDAI", "JEEP", "LEXUS", "MAZDA", "MERCEDEZ", "NISSAN",
        "PORSCHE", "TESLA", "TOYOTA", "VOLVO"
    )

    private val logoVoitures = intArrayOf(
        R.drawable.bmw, R.drawable.honda, R.drawable.hyundai, R.drawable.jeep,
        R.drawable.lexus, R.drawable.mazda, R.drawable.mercedes, R.drawable.nissan,
        R.drawable.porsche, R.drawable.tesla, R.drawable.toyota, R.drawable.volvo
    )

    fun getNomVoiture(position: Int): String {
        return nomVoiture[position]
    }

    fun getLogoVoiture(position: Int): Int {
        return logoVoitures[position]
    }


    fun getNombreVoitures(): Int {
        return nomVoiture.size
    }

    override fun montrerChargement() {
    }

    override fun cacherChargement() {
    }



}
