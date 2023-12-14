package com.example.louemonchar.presentation.voituresdisponibles

import com.example.louemonchar.VoitureMock
import com.example.louemonchar.modèle.VoitureUiModèle

class VoituresDisponiblesModèle : VoituresDisponiblesInterface {

    data class VoitureUiModèle(
        val modèle: String,
        val année: Int,
        val passagers: String,
        val propriétaire: String,
        val location: java.util.Date,
        val imageRes: Int
    )
}
