package com.example.louemonchar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.GridView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar

class ListeVoitures : Fragment() {
    private lateinit var favorisViewModel: ListeFavoris

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_liste_voitures, container, false)
        val marqueAuto = arguments?.getString("marqueAuto")

        if (marqueAuto != null) {
            val marqueTextView: TextView = view.findViewById(R.id.marqueTextView)
            marqueTextView.text = "Marque de voiture : $marqueAuto"
        } else {
            Snackbar.make(view, "Aucun modèle sélectionné", Snackbar.LENGTH_SHORT).show()
            return view
        }

        val gridView: GridView = view.findViewById(R.id.gridView)
        val modelesVoiture: Map<String, List<String>> = getModelesDeVoiture()
        val modeles = modelesVoiture[marqueAuto]

        val boutonFavoris: Button = view.findViewById(R.id.boutonFavoris)
        boutonFavoris.setOnClickListener {
            val action = ListeVoituresDirections.actionListeVoituresToFavorisFragment()
            findNavController().navigate(action)
        }

        if (modeles != null && modeles.isNotEmpty()) {
            val adapter = ArrayAdapter(
                requireContext(),
                R.layout.grid_item_layout,
                modeles
            )
            adapter.setDropDownViewResource(R.layout.grid_item_layout)
            gridView.adapter = adapter

            gridView.setOnItemClickListener { _, _, position, _ ->
                val modeleSelectionne = modeles?.get(position)
                if (modeleSelectionne != null) {
                    // Ajoutez le modèle à la liste des modèles favoris dans le ViewModel
                    favorisViewModel.modelesFavoris.add(modeleSelectionne)
                    Snackbar.make(view, "Modèle ajouté aux favoris : $modeleSelectionne", Snackbar.LENGTH_SHORT).show()
                } else {
                    Snackbar.make(view, "Aucun modèle sélectionné", Snackbar.LENGTH_SHORT).show()
                }
            }
        } else {
            // Aucun modèle n'est disponible pour cette marque, il faut donc afficher un message approprié
            val aucunModeleTextView: TextView = view.findViewById(R.id.aucunModeleTextView)
            aucunModeleTextView.visibility = View.VISIBLE
            gridView.visibility = View.GONE
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
            "Hyundai" to listOf("Hyundai Tucson", "Hyundai Elantra", "Hyundai Sonata", "Hyundai Kona", "Hyundai Venue", "Hyundai Santa Fe"),
            "Tesla" to listOf("Tesla Model X", "Tesla Model S", "Tesla Model 3", "Tesla Model Y", "Tesla Semi", "Tesla Cybertruck"),
            "Lexus" to listOf("Lexus NX 350h", "Lexus IS 500", "Lexus ES 250", "Lexus LX", "Lexus ES 350", "Lexus GX 460"),
            "Mercedes" to listOf("Mercedes-Benz Classe S", "Mercedes-Benz GLA", "Mercedes-Benz AMG GT", "Mercedes-Benz EQB", "Mercedes-Benz GLB", "Mercedes-Benz Classe C"),
            "Jeep" to listOf("Jeep Renegade North", "Jeep Cherokee", "Jeep Avenger", "Jeep Compass", "Jeep Wrangler", "Jeep Gladiator"),
            "Volvo" to listOf("Volvo XC60", "Volvo V60", "Volvo C40", "Volvo XC90", "Volvo S90", "Volvo EX30"),
            "Nissan" to listOf("Nissan Pathfinder", "Nissan Rogue", "Nissan Altima", "Nissan Frontier", "Nissan Versa", "Nissan Sentra"),
        )
        return modeles
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favorisViewModel = ViewModelProvider(requireActivity()).get(ListeFavoris::class.java)
    }

    private fun setToolbarTitle(title: String) {
        (requireActivity() as AppCompatActivity).supportActionBar?.title = title
    }
}
