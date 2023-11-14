package com.example.louemonchar.sourceDonnees

import android.util.Log

class SourceDeVoituresBidon : SourceVoitures {

    private val modelesEnregistres: MutableMap<String, MutableList<String>> = mutableMapOf()

    override fun getModelesDeVoiture(): Map<String, List<String>> {
        // Source de données bidon
        return mapOf(
            "Toyota" to listOf("Toyota Corolla", "Toyota Camry", "Toyota Rav4", "Toyota Prius", "Toyota Highlander", "Toyota Tundra"),
            "Honda" to listOf("Honda Civic", "Honda Accord", "Honda CR-V", "Honda Odyssey", "Honda Pilot", "Honda Insight"),
            "BMW" to listOf("BMW Série 3", "BMW Série 5", "BMW X5", "BMW M5", "BMW X7", "BMW Série 8"),
            "Porsche" to listOf("Porsche 911", "Porsche 718 Cayman S", "Porsche 911 GT3", "Porsche Boxster", "Porsche Taycan", "Porsche 959"),
            "Mazda" to listOf("Mazda CX5", "Mazda CX-30", "Mazda CX-9", "Mazda 3", "Mazda MX-5 RF", "Mazda 6"),
            "Hyundai" to listOf("hyundai_tucson", "hyundai_elantra", "hyundai_sonata", "hyundai_kona", "hyundai_venue", "hyundai_santa_fe"),
            "Tesla" to listOf("Tesla Model X", "Tesla Model S", "Tesla Model 3", "Tesla Model Y", "Tesla Semi", "Tesla Cybertruck"),
            "Lexus" to listOf("Lexus NX 350h", "Lexus IS 500", "Lexus ES 250", "Lexus LX", "Lexus ES 350", "Lexus GX 460"),
            "Mercedes" to listOf("Mercedes-Benz Classe S", "Mercedes-Benz GLA", "Mercedes-Benz AMG GT", "Mercedes-Benz EQB", "Mercedes-Benz GLB", "Mercedes-Benz Classe C"),
            "Jeep" to listOf("Jeep Renegade North", "Jeep Cherokee", "Jeep Avenger", "Jeep Compass", "Jeep Wrangler", "Jeep Gladiator"),
            "Volvo" to listOf("Volvo XC60", "Volvo V60", "Volvo C40", "Volvo XC90", "Volvo S90", "Volvo EX30"),
            "Nissan" to listOf("Nissan Pathfinder", "Nissan Rogue", "Nissan Altima", "Nissan Frontier", "Nissan Versa", "Nissan Sentra"),
        )
    }

    override fun getModelesEnregistres(): Map<String, List<String>> {
        return modelesEnregistres
    }

    override fun enregistrerModele(marque: String, modele: String) {
        val modelesMarque = modelesEnregistres[marque]

        if (modelesMarque != null) {
            if (!modelesMarque.contains(modele)) {
                modelesMarque.add(modele)
                modelesEnregistres[marque] = modelesMarque
            } else {
                Log.d("SourceDeVoituresBidon", "Le modèle $modele est déjà enregistré pour la marque $marque")
            }
        } else {
            modelesEnregistres[marque] = mutableListOf(modele)
        }
    }

    override fun effacerModele(marque: String, modele: String) {
        modelesEnregistres[marque]?.remove(modele)
        modelesEnregistres[marque]?.let { modelesEnregistres[marque] = it }
    }
}