package com.example.louemonchar.sourceDonnees

import android.util.Log
import com.example.louemonchar.R

class SourceDeVoituresBidon : SourceVoitures {

    private val modelesEnregistres: MutableMap<String, MutableList<String>> = mutableMapOf()
    private val proprietairesParModele: MutableMap<String, Proprietaire> = mutableMapOf()

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
            "Volvo" to listOf("Volvo XC60","Volvo V60", "Volvo C40", "Volvo XC90", "Volvo S90", "Volvo EX30"),
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
                Log.d(
                    "SourceDeVoituresBidon",
                    "Le modèle $modele est déjà enregistré pour la marque $marque"
                )
            }
        } else {
            modelesEnregistres[marque] = mutableListOf(modele)
            Log.d("SourceDeVoituresBidon", "Modèle enregistré : $modele pour la marque $marque")
        }
    }

    override fun effacerModele(marque: String, modele: String) {
        val modelesMarque = modelesEnregistres[marque]

        if (modelesMarque != null) {
            if (modelesMarque.contains(modele)) {
                modelesMarque.remove(modele)
                Log.d("SourceDeVoituresBidon", "Le modèle $modele pour la marque $marque a été effacé")
            } else {
                Log.d("SourceDeVoituresBidon", "Le modèle $modele n'est pas enregistré pour la marque $marque")
            }
        } else {
            Log.d("SourceDeVoituresBidon", "Aucun modèle enregistré pour la marque $marque")
        }
    }

    override fun assignerProprietaire(modele: String, proprietaire: Proprietaire) {
        proprietairesParModele[modele] = proprietaire
        Log.d(
            "SourceDeVoituresBidon",
            "${proprietaire.nom} est maintenant propriétaire du modèle $modele"
        )
    }

    override fun obtenirProprietaire(modele: String): ProprietaireModele? {
        val proprietaireSource = proprietairesParModele[modele]

        Log.d("SourceDeVoituresBidon", "Propriétaire récupéré : ${proprietaireSource?.nom}")

        return proprietaireSource?.let {
            val proprietaireInfo = when (modele) {
                in listOf(
                    "Tesla Modele X", "Tesla Modele S", "Tesla Modele 3", "Tesla Modele Y", "Tesla Semi", "Tesla Cybertruck",
                    "Jeep Renegade North", "Jeep Cherokee", "Jeep Avenger", "Jeep Compass", "Jeep Wrangler", "Jeep Gladiator",
                    "Nissan Pathfinder", "Nissan Rogue", "Nissan Altima", "Nissan Frontier", "Nissan Versa", "Nissan Sentra"
                ) -> {
                    Triple("Michel Lambert", "michelambert@gmail.com", "+15145551234")
                }
                in listOf(
                    "Hyundai Tucson", "Hyundai Elantra", "Hyundai Sonata", "Hyundai Kona", "Hyundai Venue", "Hyundai Santa Fe",
                    "Porsche 911", "Porsche 718 Cayman S", "Porsche 911 GT3", "Porsche Boxster", "Porsche Taycan", "Porsche 959",
                    "BMW Serie 3", "BMW Serie 5", "BMW X5", "BMW M5", "BMW X7", "BMW Serie 8"
                ) -> {
                    Triple("Robert Perron", "robperron@hotmail.com", "+15145559876")
                }
                in listOf(
                    "Toyota Corolla", "Toyota Camry", "Toyota Rav4", "Toyota Prius", "Toyota Highlander", "Toyota Tundra",
                    "Mazda CX5", "Mazda CX-30", "Mazda CX-9", "Mazda 3", "Mazda MX-5 RF", "Mazda 6",
                    "Volvo XC60","Volvo V60", "Volvo C40", "Volvo XC90", "Volvo S90", "Volvo EX30"
                ) -> {
                    Triple("Eric Desjardins", "ericdejardins@hotmail.com", "+15145556789")
                }

                else -> {Triple("Pierre Boucher", "pierreboucher@yahoo.ca", "+15145550123")}
            }

            val (nomProprietaire, email, telephone) = proprietaireInfo
            val cheminImage = when (modele) {
                in listOf(
                    "Tesla Modele X", "Tesla Modele S", "Tesla Modele 3", "Tesla Modele Y", "Tesla Semi", "Tesla Cybertruck",
                    "Jeep Renegade North", "Jeep Cherokee", "Jeep Avenger", "Jeep Compass", "Jeep Wrangler", "Jeep Gladiator",
                    "Nissan Pathfinder", "Nissan Rogue", "Nissan Altima", "Nissan Frontier", "Nissan Versa", "Nissan Sentra"
                ) -> R.drawable.elon_musk
                in listOf(
                    "Hyundai Tucson", "Hyundai Elantra", "Hyundai Sonata", "Hyundai Kona", "Hyundai Venue", "Hyundai Santa Fe",
                    "Porsche 911", "Porsche 718 Cayman S", "Porsche 911 GT3", "Porsche Boxster", "Porsche Taycan", "Porsche 959",
                    "BMW Serie 3", "BMW Serie 5", "BMW X5", "BMW M5", "BMW X7", "BMW Serie 8"
                ) -> R.drawable.robert_downey_jr
                in listOf(
                    "Toyota Corolla", "Toyota Camry", "Toyota Rav4", "Toyota Prius", "Toyota Highlander", "Toyota Tundra",
                    "Mazda CX5", "Mazda CX-30", "Mazda CX-9", "Mazda 3", "Mazda MX-5 RF", "Mazda 6",
                    "Volvo XC60","Volvo V60", "Volvo C40", "Volvo XC90", "Volvo S90", "Volvo EX30"
                ) -> R.drawable.keanu_reeves
                else -> {R.drawable.dwayne_johnson}
            }

            ProprietaireModele(
                nomProprietaire,
                email,
                telephone,
                it.horaireTravail,
                modele,
                cheminImage
            )
        }
    }



    private fun assignerModelesAProprietaire(modeles: List<String>, nomProprietaire: String, email: String, telephone: String, horaireTravail: String) {
        modeles.forEach { modele ->
            // Vérifier si le modèle est déjà assigné à un propriétaire
            if (obtenirProprietaire(modele) == null) {
                assignerProprietaire(
                    modele,
                    Proprietaire(
                        nomProprietaire,
                        email,
                        telephone,
                        horaireTravail
                    )
                )
            }
        }
    }

    init {
        data class ProprietaireInfo(
            val nom: String,
            val email: String,
            val telephone: String,
            val horaireTravail: String
        )

        val proprietairesEtModeles = mapOf(
            ProprietaireInfo(
                nom = "Éric Desjardins",
                email = "ericdejardins@hotmail.com",
                telephone = "+15145556789",
                horaireTravail = "8h - 20h, Mar-Sam"
            ) to listOf("Toyota", "Mazda", "Volvo"),

            ProprietaireInfo(
                nom = "Michel Lambert",
                email = "michelambert@gmail.com",
                telephone = "+15145551234",
                horaireTravail = "9h - 17h, Lun-Ven"
            ) to listOf("Nissan", "Jeep", "Tesla"),

            ProprietaireInfo(
                nom = "Robert Perron",
                email = "robperron@hotmail.com",
                telephone = "+15145559876",
                horaireTravail = "8h - 15h, Lun-Jeu"
            ) to listOf("Hyundai", "Porsche", "BMW"),

            ProprietaireInfo(
                nom = "Pierre Boucher",
                email = "pierreboucher@yahoo.ca",
                telephone = "+15145550123",
                horaireTravail = "8h - 18h, Lun-Ven"
            ) to listOf("Mercedes", "Honda", "Lexus")

        )

        proprietairesEtModeles.forEach { (proprietaireInfo, marques) ->
            marques.forEach { marque ->
                val modeles = getModelesDeVoiture()[marque] ?: emptyList()
                assignerModelesAProprietaire(
                    modeles,
                    proprietaireInfo.nom,
                    proprietaireInfo.email,
                    proprietaireInfo.telephone,
                    proprietaireInfo.horaireTravail
                )
            }
        }
    }

    fun obtenirDetailsProprietaire(nomProprietaire: String): Proprietaire? {
        val nomProprietaireLowerCase = nomProprietaire.toLowerCase()

        for (proprietaire in proprietairesParModele.values) {
            val proprietaireNomLowerCase = proprietaire.nom.toLowerCase()

            if (proprietaireNomLowerCase == nomProprietaireLowerCase) {
                Log.d("SourceDeVoituresBidon", "Propriétaire trouvé : ${proprietaire.nom}")
                return proprietaire
            }
        }
        Log.d("SourceDeVoituresBidon", "Aucun propriétaire correspondant trouvé pour : $nomProprietaire")
        return null
    }
}