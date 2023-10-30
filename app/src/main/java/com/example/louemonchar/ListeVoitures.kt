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
                val adapter = ArrayAdapter(
                    requireContext(),
                    R.layout.grid_item_layout,  // Utilisez le fichier de mise en page combiné ici
                    modeles
                )
                adapter.setDropDownViewResource(R.layout.grid_item_layout) // Utilisez le bon fichier de mise en page ici
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
        // Map de modèles de voitures (codage en dur)
        val modeles: Map<String, List<String>> = mapOf(
            "Toyota" to listOf("Toyota Corolla", "Toyota Camry", "Toyota Rav4", "Toyota Prius", "Toyota Highlander", "Toyota Tundra"),
            "Honda" to listOf("Honda Civic", "Honda Accord", "Honda CR-V", "Honda Odyssey", "Honda Pilot", "Honda Insight"),
            "BMW" to listOf("BMW Series 3", "BMW Series 5", "BMW X5", "BMW M5", "BMW X7", "BMW Series 5"),
            "Porsche" to listOf("Porsche 911", "Porsche 718 Cayman S", "Porsche 911 GT3", "Porsche Boxster", "Porsche Taycan", "Porsche 959"),
            "Mazda" to listOf("Mazda CX5", "Mazda CX-30", "Mazda CX-9", "Mazda 3", "Mazda MX-5 RF", "Mazda 6"),
            "Tesla" to listOf("Tesla Model X", "Tesla Model S", "Tesla Model 3", "Tesla Model Y", "Tesla Semi", "Tesla Cybertruck"),
            "Lexus" to listOf("Lexus NX 350h", "Lexus IS 500", "Lexus ES 250", "Lexus LX", "Lexus ES 350", "Lexus GX 460"),
            "Mercedes" to listOf("Mercedes-Benz Classe S", "Mercedes-Benz GLA", "Mercedes-Benz AMG GT", "Mercedes-Benz EQB", "Mercedes-Benz GLB", "Mercedes-Benz Classe C"),
            "Jeep" to listOf("Jeep Renegade North", "Jeep Cherokee", "Jeep Avenger", "Jeep Compass", "Jeep Wrangler", "Jeep Gladiator"),
            "Volvo" to listOf("Volvo XC60", "Volvo V60", "Volvo C40", "Volvo XC90", "Volvo S90", "Volvo EX30"),
            "Nissan" to listOf("Nissan Pathfinder", "Nissan Rogue", "Nissan Altima", "Nissan Frontier", "Nissan Versa", "Nissan Sentra"),
        )
        return modeles
    }

    private fun setToolbarTitle(title: String) {
        // Changer le titre de la barre d'outils ou de toute autre vue appropriée
        (requireActivity() as AppCompatActivity).supportActionBar?.title = title
    }
}
