package com.example.louemonchar.sourceDonnees

import android.util.Log

class SourceDeVoituresBidon : SourceVoitures {

    private val modelesEnregistres: MutableMap<String, MutableList<String>> = mutableMapOf()
    private val proprietairesParMarque: MutableMap<String, Proprietaire> = mutableMapOf()

    // Structure de données pour le propriétaire
    data class Proprietaire(
        val nom: String,
        val telephone: String,
        val email: String,
        val horaireTravail: String
    )

    override fun getModelesDeVoiture(): Map<String, List<String>> {
        // Source de données bidon
        return mapOf(
            "Toyota" to listOf("Toyota Corolla", "Toyota Camry", "Toyota Rav4", "Toyota Prius", "Toyota Highlander", "Toyota Tundra"),
            "Honda" to listOf("Honda Civic", "Honda Accord", "Honda CR-V", "Honda Odyssey", "Honda Pilot", "Honda Insight"),
            "BMW" to listOf("BMW Serie 3", "BMW Serie 5", "BMW X5", "BMW M5", "BMW X7", "BMW Serie 8"),
            "Porsche" to listOf("Porsche 911", "Porsche 718 Cayman S", "Porsche 911 GT3", "Porsche Boxster", "Porsche Taycan", "Porsche 959"),
            "Mazda" to listOf("Mazda CX5", "Mazda CX-30", "Mazda CX-9", "Mazda 3", "Mazda MX-5 RF", "Mazda 6"),
            "Hyundai" to listOf("Hyundai Tucson", "Hyundai Elantra", "Hyundai Sonata", "Hyundai Kona", "Hyundai Venue", "Hyundai Santa Fe"),
            "Tesla" to listOf("Tesla Modele X", "Tesla Modele S", "Tesla Modele 3", "Tesla Modele Y", "Tesla Semi", "Tesla Cybertruck"),
            "Lexus" to listOf("Lexus NX 350h", "Lexus IS 500", "Lexus ES 250", "Lexus LX", "Lexus ES 350", "Lexus GX 460"),
            "Mercedes" to listOf("Mercedes Benz Classe S", "Mercedes Benz GLA", "Mercedes Benz AMG GT", "Mercedes Benz EQB", "Mercedes Benz GLB", "Mercedes Benz Classe C"),
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
                Log.d("SourceDeVoituresBidon", "Modèle enregistré : $modele pour la marque $marque")
            } else {
                Log.d("SourceDeVoituresBidon", "Le modèle $modele est déjà enregistré pour la marque $marque")
            }
        } else {
            modelesEnregistres[marque] = mutableListOf(modele)
            Log.d("SourceDeVoituresBidon", "Modèle enregistré : $modele pour la marque $marque")
        }
    }

    override fun effacerModele(marque: String, modele: String) {
        modelesEnregistres[marque]?.remove(modele)
        modelesEnregistres[marque]?.let { modelesEnregistres[marque] = it }
    }

    override fun assignerProprietaire(marque: String, proprietaire: Proprietaire) {
        proprietairesParMarque[marque] = proprietaire
        Log.d("SourceDeVoituresBidon", "${proprietaire.nom} est maintenant propriétaire de la marque $marque")
    }

    override fun obtenirProprietaire(marque: String): Proprietaire? {
        return proprietairesParMarque[marque]
    }

    init {
        proprietairesParMarque["Hyundai"] = Proprietaire(
            "Jean Dupont",
            "+1234567890",
            "jean.dupont@hotmail.com",
            "10h - 19h, Lun-Ven"
        )

        proprietairesParMarque["Tesla"] = Proprietaire(
            "Elon Musk",
            "+1987654321",
            "elonmusk@gmail.com",
            "6h - 16h, Lun-Ven"
        )
    }
}