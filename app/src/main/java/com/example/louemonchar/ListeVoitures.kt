package com.example.louemonchar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.louemonchar.modele.ModeleVoiture
import com.example.louemonchar.sourceDonnees.SourceDeVoituresBidon
import com.example.louemonchar.sourceDonnees.SourceVoitures
import com.google.android.material.snackbar.Snackbar

class ListeVoitures : Fragment(), ModeleVoiture.ModeleClickListener {
    private lateinit var sourceVoitures: SourceVoitures

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sourceVoitures = SourceDeVoituresBidon()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_liste_voitures, container, false)
        val marqueAuto = requireArguments().getString("marqueAuto")

        if (marqueAuto != null) {
            val marqueTextView: TextView = view.findViewById(R.id.marqueTextView)
            marqueTextView.text = "Marque de voiture : $marqueAuto"
        } else {
            Snackbar.make(view, "Aucun modèle sélectionné", Snackbar.LENGTH_SHORT).show()
            return view
        }

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)

        val modelesVoiture: Map<String, List<String>> = sourceVoitures.getModelesDeVoiture()
        val modeles = modelesVoiture[marqueAuto]

        val boutonEnregistres: Button = view.findViewById(R.id.boutonEnregistres)
        boutonEnregistres.setOnClickListener {
            val action = ListeVoituresDirections.actionListeVoituresToEnregistrementsFragment()
            findNavController().navigate(action)
        }

        if (!modeles.isNullOrEmpty()) {
            recyclerView.visibility = View.VISIBLE
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            val adapter = ModeleVoiture(modeles, this)
            recyclerView.adapter = adapter
            val layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.HORIZONTAL, false)
            recyclerView.layoutManager = layoutManager
        } else {
            recyclerView.visibility = View.GONE
        }

        return view
    }

    override fun onModeleClick(modele: String) {
        sourceVoitures.enregistrerModele(requireArguments().getString("marqueAuto")!!, modele)
        if (sourceVoitures.getModelesDeVoiture()["marqueAuto"]?.contains(modele) == true) {
            Snackbar.make(requireView(), "Le modèle est déjà enregistré : $modele", Snackbar.LENGTH_SHORT).show()
        } else {
            sourceVoitures.enregistrerModele("marqueAuto", modele)
            Snackbar.make(requireView(), "Modèle enregistré : $modele", Snackbar.LENGTH_SHORT).show()
        }
    }
}
