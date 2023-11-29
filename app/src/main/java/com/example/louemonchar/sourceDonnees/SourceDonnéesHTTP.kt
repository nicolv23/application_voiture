package com.example.louemonchar.sourceDonnees


import android.content.Context
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*


class SourceDonnéesHTTP(private val context: Context) : SourceVoitures {

    private val client = HttpClient(OkHttp)

    private val sourceDeVoituresBidon = SourceDeVoituresBidon(context)
    override fun getModelesDeVoiture(): Map<String, List<String>> {
        TODO("Not yet implemented")
    }

    override fun getModelesEnregistres(): Map<String, List<String>> {
        return sourceDeVoituresBidon.getModelesEnregistres()
    }

    override fun enregistrerModele(marque: String, modele: String) {
        sourceDeVoituresBidon.enregistrerModele(marque, modele)
    }

    override fun effacerModele(marque: String, modele: String) {
        sourceDeVoituresBidon.effacerModele(marque, modele)
    }

    override fun assignerProprietaire(marque: String, proprietaire: SourceDeVoituresBidon.Proprietaire) {
        sourceDeVoituresBidon.assignerProprietaire(marque, proprietaire)
    }

    override fun obtenirProprietaire(marque: String): ProprietaireModele? {
        return sourceDeVoituresBidon.obtenirProprietaire(marque)
    }

    fun onDestroy() {
        client.close()
    }
}
