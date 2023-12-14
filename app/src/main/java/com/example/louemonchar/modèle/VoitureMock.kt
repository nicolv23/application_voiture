package com.example.louemonchar


import com.example.louemonchar.modèle.VoitureUiModèle
import java.text.SimpleDateFormat
import java.util.Locale


object VoitureMock  { //temp



    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    // Créez une liste de voitures pour chaque mois de l'année
    private val hyundai = listOf(
        VoitureUiModèle(R.drawable.hyundai_elantra, "HYUNDAI", dateFormat.parse("2022-01-01")!!, dateFormat.parse("2023-12-28")!!, "6 Passagers", "John Doe"),
        VoitureUiModèle(R.drawable.hyundai_kona, "HYUNDAI", dateFormat.parse("2023-01-15")!!, dateFormat.parse("2023-12-27")!!, "6 Passagers", "Billy Curtis"),
        // Ajoutez d'autres voitures pour le mois de janvier
    )

    private val mercedez = listOf(
        VoitureUiModèle(R.drawable.mercedes_benz_amg_gt, "MERCEDEZ", dateFormat.parse("2022-02-05")!!, dateFormat.parse("2023-12-230")!!, "4 Passagers", "Johnny Lafleur"),
        VoitureUiModèle(R.drawable.mercedes_benz_classe_s, "MERCEDEZ", dateFormat.parse("2022-02-20")!!, dateFormat.parse("2023-12-24")!!, "4 Passagers", "Nicolas Surplus"),
        // Ajoutez d'autres voitures pour le mois de février
    )

    private val tesla = listOf(
        VoitureUiModèle(R.drawable.tesla_modele_x, "TESLA", dateFormat.parse("2023-02-05")!!, dateFormat.parse("2023-12-228")!!,"4 Passagers", "Antoine Jolicoeur"),
        VoitureUiModèle(R.drawable.tesla_cybertruck, "TESLA", dateFormat.parse("2024-02-20")!!, dateFormat.parse("2023-12-27")!!, "4 Passagers", "Young Bigboss"),
        // Ajoutez d'autres voitures pour le mois de février
    )

    private val bmw = listOf(
        VoitureUiModèle(R.drawable.bmw_serie_3, "BMW", dateFormat.parse("2024-11-05")!!, dateFormat.parse("2023-12-23")!!,"4 Passagers", "Patrick Auboncoeur"),

        // Ajoutez d'autres voitures pour le mois de février
    )

    private val toyota = listOf(
        VoitureUiModèle(R.drawable.toyota_corolla, "TOYOTA", dateFormat.parse("2021-02-05")!!, dateFormat.parse("2023-12-31")!!, "4 Passagers", "Juli Pyruss"),
        VoitureUiModèle(R.drawable.toyota_prius, "TOYOTA", dateFormat.parse("2024-02-20")!!, dateFormat.parse("2024-12-02")!!, "4 Passagers", "Annie Auguste"),
        // Ajoutez d'autres voitures pour le mois de février
    )

    // ... Répétez pour chaque mois de l'année

    private val voitureList: List<VoitureUiModèle> by lazy {
        mutableListOf<VoitureUiModèle>().apply {
            addAll(hyundai)
            addAll(mercedez)
            addAll(tesla)
            addAll(bmw)
            addAll(toyota)

            // Ajoutez les autres listes ici pour les autres mois
            // ...
        }
    }

    // Fonction d'accès à la liste complète
    fun getListeVoiture(): List<VoitureUiModèle> {
        return voitureList
    }


    // Fonction pour charger les modèles enregistrés pour une marque spécifique
    fun chargerModelesEnregistres(marque: String): List<String> {
        return getListeVoiture()
            .filter { it.propriétaire == marque }
            .map { it.modèle }
    }
}

