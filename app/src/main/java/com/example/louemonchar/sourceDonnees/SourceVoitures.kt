package com.example.louemonchar.sourceDonnees

interface SourceVoitures {
    fun getModelesDeVoiture(): Map<String, List<String>>
    fun getModelesEnregistres(): Map<String, List<String>>
    fun enregistrerModele(marque: String, modele: String)
    fun effacerModele(marque: String, modele: String)
    fun assignerProprietaire(modele: String, proprietaire: SourceDeVoituresBidon.Proprietaire)
    fun obtenirProprietaire(marque: String): ProprietaireModele?
}
