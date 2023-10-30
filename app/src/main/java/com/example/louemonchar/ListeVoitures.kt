package com.example.louemonchar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


class ListeVoitures : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_liste_voitures, container, false)

        val marqueAuto = arguments?.getString("marqueAuto")

        if (marqueAuto != null) {
            val marqueTextView: TextView = view.findViewById(R.id.marqueTextView)
            marqueTextView.text = "Marque de voiture : $marqueAuto"

            val modeleGridView: GridView = view.findViewById(R.id.gridView)
            val modelesVoiture: Map<String, List<String>> = getModelesDeVoiture()
            val modeles = modelesVoiture[marqueAuto]

            if (modeles != null && modeles.isNotEmpty()) {
                val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, modeles)
                modeleGridView.adapter = adapter
            } else {
                // Aucun modèle n'est disponible pour cette marque, affichez un message approprié
                val aucunModeleTextView: TextView = view.findViewById(R.id.aucunModeleTextView)
                aucunModeleTextView.visibility = View.VISIBLE
                modeleGridView.visibility = View.GONE
            }
        } else {
            // Gérer le cas où l'argument "marqueAuto" est null (traitement d'erreur)
            val erreurTextView: TextView = view.findViewById(R.id.marqueTextView)
            erreurTextView.text = "Erreur : Marque de voiture non spécifiée."
        }

        return view
    }

    private fun getModelesDeVoiture(): Map<String, List<String>> {
        // Définir votre map de modèles de voiture ici
        val modeles: Map<String, List<String>> = mapOf(
            "Toyota" to listOf("Corolla", "Camry", "Rav4"),
            "Honda" to listOf("Civic", "Accord", "CR-V"),
            "BMW" to listOf("Series 3", "Series 5", "X5"),
            "Porsche" to listOf("Modele 1", "Modele 2", "Modele 3")
        )
        return modeles
    }

    private fun setToolbarTitle(title: String) {
        // Changer le titre de la barre d'outils ou de toute autre vue appropriée
        (requireActivity() as AppCompatActivity).supportActionBar?.title = title
    }
}



