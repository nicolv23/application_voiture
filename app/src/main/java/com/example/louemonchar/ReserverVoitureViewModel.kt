package com.example.louemonchar

// Importation nécessaire pour utiliser les fonctionnalités ViewModel d'Android
import androidx.lifecycle.ViewModel

// Définition de la classe ReserverVoitureViewModel qui hérite de ViewModel
class ReserverVoitureViewModel : ViewModel() {
    // Déclaration d'une liste mutable pour stocker les voitures réservées
    val voituresReservees = mutableListOf<VoitureReservee>()
}
